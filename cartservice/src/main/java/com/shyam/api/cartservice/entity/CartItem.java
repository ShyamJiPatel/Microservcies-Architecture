package com.shyam.api.cartservice.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.shyam.commonlib.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "cart_items")
@Entity
public class CartItem extends BaseEntity {

	@Column(name = "subtotal")
	private BigDecimal subtotal;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "discount")
	private BigDecimal discount;

	@Column(name = "product_id", nullable = false)
	private Long productId;

	@Column(name = "tax")
	private BigDecimal tax;

	@Transient
	private Product product;

	public CartItem() {

	}

	public CartItem(Product product, Integer quantity, BigDecimal subtotal) {
		this.product = product;
		this.productId = product.getId();
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
}
