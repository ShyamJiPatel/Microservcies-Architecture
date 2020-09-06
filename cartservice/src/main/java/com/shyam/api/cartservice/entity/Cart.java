package com.shyam.api.cartservice.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.shyam.commonlib.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "cart")
@Entity
public class Cart extends BaseEntity {

	@Column(name = "subtotal")
	private BigDecimal subtotal = new BigDecimal(0);

	@Column(name = "discount")
	private BigDecimal discount = new BigDecimal(0);

	@Column(name = "tax")
	private BigDecimal tax = new BigDecimal(0);

	@Column(name = "total")
	private BigDecimal total = new BigDecimal(0);

	@Column(name = "delivery_charges")
	private BigDecimal deliveryCharges = new BigDecimal(0);

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private Set<CartItem> items;

	public Cart() {
		this.items = new HashSet<CartItem>();
	}

	public Cart(Long userId) {
		this.userId = userId;
		this.items = new HashSet<CartItem>();
	}

}
