package com.ojas.request;

import java.util.List;

import com.ojas.model.OffenceType;

public class OffenceRequest {

	private List<OffenceType> offenceType;

	public OffenceRequest() {
		super();
	}

	public OffenceRequest(List<OffenceType> offenceType) {
		super();
		this.offenceType = offenceType;
	}

	public List<OffenceType> getOffenceType() {
		return offenceType;
	}

	public void setOffenceType(List<OffenceType> offenceType) {
		this.offenceType = offenceType;
	}

	@Override
	public String toString() {
		return "OffenceRequest [offenceType=" + offenceType + "]";
	}

}
