package com.shyam.api.cartservice.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

	@ManyToMany(cascade = CascadeType.ALL)
	private List<CartItem> items;

}
