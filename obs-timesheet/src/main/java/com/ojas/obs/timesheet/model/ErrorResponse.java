package com.ojas.obs.timesheet.model;

public class ErrorResponse {

	private String statusCode;
	private String statusMessage;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}


}
