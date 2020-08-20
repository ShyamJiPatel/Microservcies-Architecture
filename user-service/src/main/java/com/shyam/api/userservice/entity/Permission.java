package com.shyam.api.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.shyam.commonlib.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {

	@Column(name = "name")
	private String name;

	public Permission() {

	}

	public Permission(String name) {
		this.name = name;
	}
}
