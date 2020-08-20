package com.shyam.api.cartservice.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

	@Column(name = "tax")
	private BigDecimal tax;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Product product;
}
