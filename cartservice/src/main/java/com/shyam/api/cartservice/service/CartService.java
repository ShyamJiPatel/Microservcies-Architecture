package com.shyam.api.cartservice.service;

import java.util.Optional;

import com.shyam.api.cartservice.entity.Cart;

public interface CartService {

	Optional<Cart> getCart(Long userId);

	Cart createCart(Long userId);

	void addItemToCart(Long userId, Long productId, Integer quantity);

	boolean checkIfItemExist(Cart cart, Long productId);

	void changeItemQuantity(Cart cart, Long productId, Integer quantity);

	void addItemToCart(Cart cart, Long productId, Integer quantity);

	void removeItemFromCart(Long userId, Long productId);

	void deleteCart(Long userId);

}
