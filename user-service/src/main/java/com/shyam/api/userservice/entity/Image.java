package com.shyam.api.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "images")
@Data
@EqualsAndHashCode(callSuper = false)
public class Image extends BaseEntity {

	@Column(name = "created_name")
	private String createdName;

	@Column(name = "original_name")
	private String originalName;

	@Column(name = "extension")
	private String extension;

	@Column(name = "size")
	private String size;

	@Column(name = "other")
	private String other;

	@Column(name = "content_type")
	private String contentType;

	@Column(name = "type")
	private String type;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private UserDetails user;

	@Transient
	private transient String url;

	public Image() {

	}

	public Image(String createdName, String originalName, String extension, String contentType) {
		this.createdName = createdName;
		this.originalName = originalName;
		this.extension = extension;
		this.contentType = contentType;
	}
}
