package com.ojas.service;

import org.springframework.http.ResponseEntity;

import com.ojas.request.VehicleTypesRequest;

public interface VehicleService {
	public ResponseEntity<Object> savevehicleTypesRequest(VehicleTypesRequest vehicleTypesRequest);

	public ResponseEntity<Object> editVehicle(VehicleTypesRequest vehicleTypesRequest, String id);

	public ResponseEntity<Object> deleteVehicleById(String id);

	public ResponseEntity<Object> getAllVehicle();

}
