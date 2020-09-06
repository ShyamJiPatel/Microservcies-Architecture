package com.shyam.api.cartservice.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.api.cartservice.constants.AppMessage;
import com.shyam.api.cartservice.dao.CartDao;
import com.shyam.api.cartservice.entity.Cart;
import com.shyam.api.cartservice.entity.CartItem;
import com.shyam.api.cartservice.entity.Product;
import com.shyam.api.cartservice.entity.UserDetails;
import com.shyam.api.cartservice.feignclient.ProductClient;
import com.shyam.api.cartservice.feignclient.UserClient;
import com.shyam.api.cartservice.service.CartService;
import com.shyam.api.cartservice.utilities.CartUtilities;
import com.shyam.commonlib.entity.CustomResponse;
import com.shyam.commonlib.exception.ResourceNotFoundException;
import com.shyam.commonlib.util.CommonUtil;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private UserClient userClient;

	@Override
	public Optional<Cart> getCart(Long userId) {
		return cartDao.findByUserId(userId);
	}

	@Override
	public Cart createCart(Long userId) {
		UserDetails user = getUser(userId);
		if (user.getActive() == null || user.getActive() == 0) {
			throw new ResourceNotFoundException(AppMessage.USER_NOT_ACTIVATED);
		}

		Cart cart = new Cart(userId);
		return cartDao.save(cart);
	}
	
	@Override
	public void deleteCart(Long userId) {
		cartDao.deleteByUserId(userId);
	}

	@Transactional
	@Override
	public void addItemToCart(Long userId, Long productId, Integer quantity) {
		Cart cart = getOrCreateCart(userId);
		Set<CartItem> cartItems = cart.getItems();
		if (cartItems == null || cartItems.size() == 0) {
			addItemToCart(cart, productId, quantity);
		} else {
			boolean exist = checkIfItemExist(cart, productId);
			if (exist) {
				changeItemQuantity(cart, productId, quantity);
			} else {
				addItemToCart(cart, productId, quantity);
			}
		}
		cartDao.save(cart);
	}

	@Override
	public void addItemToCart(Cart cart, Long productId, Integer quantity) {
		Product product = getProduct(productId);
		CartItem cartItem = new CartItem(product, quantity, CartUtilities.getSubTotalForItem(product, quantity));
		cart.getItems().add(cartItem);
		calculateCartTotal(cart);
	}

	@Override
	public void changeItemQuantity(Cart cart, Long productId, Integer quantity) {
		for (CartItem cartItem : cart.getItems()) {
			if (cartItem.getProductId().equals(productId)) {
				cartItem.setQuantity(quantity);
				Product product = cartItem.getProduct() == null ? getProduct(productId) : cartItem.getProduct();
				cartItem.setProduct(product);
				cartItem.setSubtotal(CartUtilities.getSubTotalForItem(product, cartItem.getQuantity()));
			}
		}
		calculateCartTotal(cart);
	}

	@Override
	public boolean checkIfItemExist(Cart cart, Long productId) {
		for (CartItem cartItem : cart.getItems()) {
			if (cartItem.getProductId().equals(productId)) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public void removeItemFromCart(Long userId, Long productId) {
		Cart cart = fetchCart(userId);
		Set<CartItem> cartItems = cart.getItems();
		if (cartItems != null && cartItems.size() > 0) {
			boolean exist = checkIfItemExist(cart, productId);
			if (exist) {
				removeItemFromCart(cart, productId);
			} else {
				throw new RuntimeException(AppMessage.PRODUCT_NOT_FOUND_IN_CART);
			}
		}
		cartDao.save(cart);
	}

	private void removeItemFromCart(Cart cart, Long productId) {
		Set<CartItem> remainingItems = new HashSet<CartItem>();
		for (CartItem cartItem : cart.getItems()) {
			if (!cartItem.getProductId().equals(productId)) {
				remainingItems.add(cartItem);
			}
		}
		cart.setItems(remainingItems);
		calculateCartTotal(cart);
	}

	private Product getProduct(Long productId) {
		CustomResponse productResponse = productClient.findById(productId);
		if (productResponse.getSuccess()) {
			Product product = CommonUtil.getObjectFromLinkedHashMap(Product.class, productResponse.getData());
			if (product == null) {
				throw new ResourceNotFoundException(AppMessage.PRODUCT_NOT_FOUND);
			}
			return product;
		} else {
			throw new RuntimeException(productResponse.getMessage());
		}
	}

	private UserDetails getUser(Long userId) {
		CustomResponse userResponse = userClient.findById(userId);

		if (userResponse.getSuccess()) {
			UserDetails user = CommonUtil.getObjectFromLinkedHashMap(UserDetails.class, userResponse.getData());

			if (user == null) {
				throw new ResourceNotFoundException(AppMessage.USER_NOT_FOUND);
			}
			return user;
		} else {
			throw new RuntimeException(userResponse.getMessage());
		}
	}

	private Cart fetchCart(Long userId) {
		Optional<Cart> dbCart = getCart(userId);

		if (dbCart.isPresent()) {
			return dbCart.get();
		} else {
			throw new ResourceNotFoundException(AppMessage.CART_NOT_FOUND);
		}
	}

	private Cart getOrCreateCart(Long userId) {
		Optional<Cart> dbCart = getCart(userId);

		Cart cart = null;
		if (dbCart.isPresent()) {
			cart = dbCart.get();
		} else {
			cart = createCart(userId);
		}

		return cart;
	}

	private void calculateCartTotal(Cart cart) {

		BigDecimal subtotal = new BigDecimal(0);
		BigDecimal tax = new BigDecimal(0);
		BigDecimal deliveryCharges = new BigDecimal(0);
		BigDecimal discount = new BigDecimal(0);

		for (CartItem cartItem : cart.getItems()) {

			subtotal = subtotal.add(cartItem.getSubtotal());
			tax = tax.add(cartItem.getTax());
			deliveryCharges = deliveryCharges.add(cartItem.getDeliveryCharges());
			discount = discount.add(cartItem.getDiscount());

			cart.setSubtotal(subtotal);
			cart.setTax(tax);
			cart.setDeliveryCharges(deliveryCharges);
			cart.setDiscount(discount);

			cart.setTotal(subtotal.add(tax).add(deliveryCharges).subtract(discount));
		}

	}

}
