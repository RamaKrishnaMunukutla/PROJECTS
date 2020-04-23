package com.ojas.response;

import java.util.List;

import com.ojas.model.OffenceType;

public class OffenceResponse {

	private List<OffenceType> offence;
	private String status;
	private String message;

	public OffenceResponse() {
		super();
	}

	public OffenceResponse(List<OffenceType> offence, String status, String message) {
		super();
		this.offence = offence;
		this.status = status;
		this.message = message;
	}

	public List<OffenceType> getOffence() {
		return offence;
	}

	public void setOffence(List<OffenceType> offence) {
		this.offence = offence;
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

	@Override
	public String toString() {
		return "OffenceResponse [offence=" + offence + ", status=" + status + ", message=" + message + "]";
	}

}
