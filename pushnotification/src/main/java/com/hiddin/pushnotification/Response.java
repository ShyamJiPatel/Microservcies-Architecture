package com.hiddin.pushnotification;

public class Response {

	private Boolean success;
	private String status;
	private String message;
	private Object data;
	private String timestamp;

	public Response() {
		markSuccess("");
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void markFailure(String message) {
		this.success = false;
		this.message = message;
		this.status = "failure";
		this.timestamp = String.valueOf(System.currentTimeMillis());
	}

	public void markSuccess(String message) {
		this.message = message;
		this.success = true;
		this.status = "success";
		this.timestamp = String.valueOf(System.currentTimeMillis());
	}

	@Override
	public String toString() {
		return "Response [success=" + success + ", status=" + status + ", message=" + message + ", data=" + data
				+ ", timestamp=" + timestamp + "]";
	}

}
