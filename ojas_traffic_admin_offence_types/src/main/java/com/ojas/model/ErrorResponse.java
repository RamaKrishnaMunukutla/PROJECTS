package com.ojas.model;

public class ErrorResponse {

	private String message;
	private Integer status;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [status=" + status + ", message=" + message + "]";
	}

}
