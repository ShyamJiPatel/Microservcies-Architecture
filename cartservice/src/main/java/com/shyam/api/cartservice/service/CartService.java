package com.shyam.api.cartservice.service;

import java.util.Optional;

import com.shyam.api.cartservice.entity.Cart;

public interface CartService {

	public Optional<Cart> getCart(Long userId);

	public Cart createCart(Long userId);

	public void addItemToCart(Long userId, Long productId, Integer quantity);

	boolean checkIfItemExist(Cart cart, Long productId);

	void changeItemQuantity(Cart cart, Long productId, Integer quantity);

	void addItemToCart(Cart cart, Long productId, Integer quantity);

}
