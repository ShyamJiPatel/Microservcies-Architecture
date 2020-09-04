package com.shyam.api.cartservice.utilities;

import java.math.BigDecimal;

import com.shyam.api.cartservice.entity.Product;

public class CartUtilities {

	public static BigDecimal getSubTotalForItem(Product product, int quantity) {
		return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
	}
}