package com.shyam.api.userservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "created_on")
	@CreationTimestamp
	private LocalDateTime createdOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_on")
	@UpdateTimestamp
	private LocalDateTime updatedOn;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "archived")
	private Boolean archived = false;

	@Column(name = "version")
	@Version
	private Long version;

}
