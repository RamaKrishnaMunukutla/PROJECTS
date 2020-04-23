package com.ojas.response;

import java.util.List;

import com.ojas.model.VehicleType;

public class VehicleTypeResponse {

	private String message;
	private Integer status;
	private List<VehicleType> vehicleType;

	public VehicleTypeResponse() {
		super();
	}
	public VehicleTypeResponse(List<VehicleType> vehicleType, Integer status, String message) {
		super();
		this.vehicleType = vehicleType;
		this.status = status;
		this.message = message;
	}


	public List<VehicleType> getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(List<VehicleType> vehicleType) {
		this.vehicleType = vehicleType;
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

}
