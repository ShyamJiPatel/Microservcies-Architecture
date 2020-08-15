package com.shyam.commonlib.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CustomResponse {

	private String status;
	private Boolean success;
	private String message;
	private String details;
	private Object data;
	private LocalDateTime timestamp;

	public CustomResponse() {
		this.success = true;
		this.status = "success";
		this.timestamp = LocalDateTime.now();
	}

	public CustomResponse(Object data) {
		this.data = data;
		this.success = true;
		this.status = "success";
		this.timestamp = LocalDateTime.now();
	}

	public CustomResponse(String message, String details, Boolean success) {
		this.message = message;
		this.details = details;
		this.success = success;
		if (success) {
			this.status = "success";
		} else {
			this.status = "failure";
		}
		this.timestamp = LocalDateTime.now();
	}

	public void markFailure(String message) {
		this.message = message;
		this.success = false;
		this.status = "failure";
		this.timestamp = LocalDateTime.now();
	}

	public void markSuccess(String message) {
		this.message = message;
		this.success = true;
		this.status = "success";
		this.timestamp = LocalDateTime.now();
	}

}
