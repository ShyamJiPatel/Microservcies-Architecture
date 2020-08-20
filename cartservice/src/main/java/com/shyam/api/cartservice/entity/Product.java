package com.shyam.api.cartservice.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

	@Column(name = "brand")
	private String brand;

	@Column(name = "size")
	private String size;

	@Column(name = "price")
	private BigDecimal price;
	
}
