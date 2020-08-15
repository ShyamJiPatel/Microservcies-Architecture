package com.shyam.api.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shyam.api.userservice.enums.AddressType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	private String phone;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "houseNo")
	private String houseNo;

	@Column(name = "locality")
	private String locality;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "city")
	private String city;

	@Column(name = "district")
	private String district;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "is_default")
	private Boolean isDefault;

	@Column(name = "open_on_sat")
	private Boolean openOnSat = false;

	@Column(name = "open_on_sun")
	private Boolean openOnSun = false;

	@Column(name = "type")
	@NotNull(message = "Address must have be a type")
	@Enumerated(EnumType.STRING)
	private AddressType type;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private UserDetails user;

}
