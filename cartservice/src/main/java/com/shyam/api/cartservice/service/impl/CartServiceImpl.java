package com.shyam.api.cartservice.service.impl;

import java.util.List;
import java.util.Optional;

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
		CustomResponse userResponse = userClient.findById(userId);

		if (userResponse.getSuccess()) {
			UserDetails user = (UserDetails) userResponse.getData();
			if (user == null) {
				throw new ResourceNotFoundException(AppMessage.USER_NOT_FOUND);
			} else if (user.getActive() == null || user.getActive() == 0) {
				throw new ResourceNotFoundException(AppMessage.USER_NOT_ACTIVATED);
			}
		} else {
			throw new RuntimeException(userResponse.getMessage());
		}

		Cart cart = new Cart(userId);
		return cartDao.save(cart);
	}

	@Transactional
	@Override
	public void addItemToCart(Long userId, Long productId, Integer quantity) {
		Optional<Cart> dbCart = getCart(userId);

		Cart cart = null;
		if (dbCart.isPresent()) {
			cart = dbCart.get();
		} else {
			cart = createCart(userId);
		}

		List<CartItem> cartItems = cart.getItems();
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
		CustomResponse productResponse = productClient.findById(productId);

		if (productResponse.getSuccess()) {
			Product product = (Product) productResponse.getData();
			if (product == null) {
				throw new ResourceNotFoundException(AppMessage.PRODUCT_NOT_FOUND);
			}
			CartItem cartItem = new CartItem(product, quantity, CartUtilities.getSubTotalForItem(product, quantity));
			cart.getItems().add(cartItem);
		} else {
			throw new RuntimeException(productResponse.getMessage());
		}
	}

	@Override
	public void changeItemQuantity(Cart cart, Long productId, Integer quantity) {
		for (CartItem cartItem : cart.getItems()) {
			if (cartItem.getProductId().equals(productId)) {
				cartItem.setQuantity(cartItem.getQuantity() + quantity);
				cartItem.setSubtotal(CartUtilities.getSubTotalForItem(cartItem.getProduct(), cartItem.getQuantity()));
			}
		}
	}

	@Override
	public boolean checkIfItemExist(Cart cart, Long productId) {
		for (CartItem cartItem : cart.getItems()) {
			if (cartItem.getProduct().getId().equals(productId)) {
				return true;
			}
		}
		return false;
	}

}
