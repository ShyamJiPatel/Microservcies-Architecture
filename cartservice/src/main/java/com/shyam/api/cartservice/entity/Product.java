package com.shyam.api.cartservice.entity;

import java.math.BigDecimal;

import com.shyam.commonlib.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseEntity {
	private BigDecimal price;
}
