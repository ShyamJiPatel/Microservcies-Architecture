package com.shyam.api.cartservice.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.shyam.commonlib.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "cart")
@Entity
public class Cart extends BaseEntity {

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "discount")
	private BigDecimal discount;

	@Column(name = "tax")
	private BigDecimal tax;

	@Column(name = "total")
	private BigDecimal total;

	@Column(name = "delivery_charges")
	private BigDecimal delivery_charges;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cart_cart_items", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "cart_item_id"))
	private List<CartItem> items;

	public Cart() {
		this.items = new ArrayList<CartItem>();
	}

	public Cart(Long userId) {
		this.userId = userId;
		this.items = new ArrayList<CartItem>();
	}

}
