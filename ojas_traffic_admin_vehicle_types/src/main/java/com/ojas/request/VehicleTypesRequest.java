package com.ojas.request;

import java.util.List;

import com.ojas.model.VehicleType;

public class VehicleTypesRequest {
	
	List<VehicleType> vehicleTypes;

	public VehicleTypesRequest() {
		super();
	}

	public VehicleTypesRequest(List<VehicleType> vehicleTypes) {
		super();
		this.vehicleTypes = vehicleTypes;
	}

	public List<VehicleType> getVehicleTypes() {
		return vehicleTypes;
	}

	public void setVehicleTypes(List<VehicleType> vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}

}
