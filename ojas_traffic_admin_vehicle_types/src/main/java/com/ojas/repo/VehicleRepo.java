package com.ojas.repo;

import java.util.List;

import com.ojas.model.VehicleType;
import com.ojas.request.VehicleTypesRequest;

public interface VehicleRepo {
	
	public VehicleType saveVehicle(VehicleTypesRequest vehicleTypesRequest);

	public VehicleType editVehicle(VehicleTypesRequest vehicleTypesRequest, String id);

	public boolean deleteVehicle(String id);

	public List<VehicleType> getAll();

}
