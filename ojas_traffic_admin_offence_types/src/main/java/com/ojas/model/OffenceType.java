package com.ojas.model;

import org.springframework.data.annotation.Id;

public class OffenceType {
	@Id
	private String id;
	private String offences;

	public OffenceType() {
		super();
	}

	public OffenceType(String id, String offences) {
		super();
		this.id = id;
		this.offences = offences;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOffences() {
		return offences;
	}

	public void setOffences(String offences) {
		this.offences = offences;
	}

	@Override
	public String toString() {
		return "OffenceType [id=" + id + ", offences=" + offences + "]";
	}

}
