package com.shyam.api.productservice.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.shyam.api.productservice.enums.Currency;
import com.shyam.api.productservice.enums.Size;
import com.shyam.commonlib.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "color")
	private String color;

	@Column(name = "description")
	@Lob
	private String description;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "brand")
	private String brand;

	@Column(name = "size")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Product must have a size")
	private Size size;

	@Column(name = "currency")
	@Enumerated(EnumType.STRING)
	private Currency currency;

	@ManyToOne(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	@Transient
	private List<Image> products;
}
