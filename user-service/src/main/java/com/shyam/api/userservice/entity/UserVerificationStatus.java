package com.shyam.api.userservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.shyam.api.userservice.enums.UserVerificationType;
import com.shyam.commonlib.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user_verification_status")
public class UserVerificationStatus extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserDetails user;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private UserVerificationType type;

	@Column(name = "code")
	private String code;

	@Column(name = "verified")
	private Boolean verified;

	@Column(name = "verified_on")
	private LocalDateTime verifiedOn;

	@Column(name = "expiry_date")
	private LocalDateTime expiryDate;

}
