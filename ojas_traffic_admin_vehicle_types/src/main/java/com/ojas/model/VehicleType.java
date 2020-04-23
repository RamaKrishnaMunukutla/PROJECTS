package com.ojas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class VehicleType {
	@Id
	private String id;
	private String vehicleTypes;

	public VehicleType() {
		super();
	}

	public VehicleType(String id, String vehicleTypes) {
		super();
		this.id = id;
		this.vehicleTypes = vehicleTypes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVehicleTypes() {
		return vehicleTypes;
	}

	public void setVehicleTypes(String vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}

	@Override
	public String toString() {
		return "VehicleType [id=" + id + ", vehicleTypes=" + vehicleTypes + "]";
	}

}
