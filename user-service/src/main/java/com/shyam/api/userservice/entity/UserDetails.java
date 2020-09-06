package com.shyam.api.userservice.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shyam.commonlib.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "user_details", uniqueConstraints = { @UniqueConstraint(columnNames = { "email", "archived" }) })
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDetails extends BaseEntity {

	@Column(name = "username")
	private String username;

	@NotNull
	@Size(min = 2, message = "First Name should have atleast 2 characters")
	@Column(name = "firstName")
	private String firstName;

	@Column(name = "middleName")
	private String middleName;

	@Column(name = "lastName")
	private String lastName;

	@NotNull
	@Email(message = "Invalid email address")
	@Column(name = "email")
	private String email;

	@Column(name = "aboutMe")
	private String aboutMe;

	@Column(name = "gender")
	private String gender;

	@Column(name = "dob")
	private String dob;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "timeZone")
	private String timeZone;

	@Column(name = "position")
	private String position;

	@Column(name = "active")
	private Integer active;

	@Column(name = "verified")
	private Boolean verified;

	@Column(name = "last_successful_login")
	private String lastSuccessfulLogin;

	@Column(name = "last_failed_login")
	private LocalDateTime lastFailedLogin;

	@Column(name = "last_password_reset")
	private LocalDateTime lastPasswordReset;

	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

}
