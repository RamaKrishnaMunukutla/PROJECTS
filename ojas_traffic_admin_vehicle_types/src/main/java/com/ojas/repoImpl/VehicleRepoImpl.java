package com.ojas.repoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.model.VehicleType;
import com.ojas.repo.VehicleRepo;
import com.ojas.request.VehicleTypesRequest;

@Repository
public class VehicleRepoImpl implements VehicleRepo {

	@Autowired
	private MongoTemplate mt;

	@Autowired
	private Environment env;

	public VehicleType saveVehicle(VehicleTypesRequest vehicleTypesRequest) {

		List<VehicleType> vehicleTypes = vehicleTypesRequest.getVehicleTypes();
		VehicleType result = null;
		
		for (VehicleType vehicleType : vehicleTypes) {
			result = mt.insert(vehicleType, env.getProperty("vehicle-collection-name"));

		}
		return result;
	}

	public VehicleType editVehicle(VehicleTypesRequest vehicleTypesRequest, String id) {

		VehicleType obj = mt.findById(id, VehicleType.class, env.getProperty("vehicle-collection-name"));

		obj.setId(id);
		String vehicleTypes = vehicleTypesRequest.getVehicleTypes().get(0).getVehicleTypes();

		obj.setVehicleTypes(vehicleTypes);

		return mt.save(obj, env.getProperty("vehicle-collection-name"));

	}

	public boolean deleteVehicle(String id) {
	
		VehicleType obj = mt.findById(id, VehicleType.class, env.getProperty("vehicle-collection-name"));
		
		if (obj == null) {
			throw new NullPointerException();
		} else {
			mt.remove(obj, env.getProperty("vehicle-collection-name"));
			return true;
		}
	}

	@Override
	public List<VehicleType> getAll() {
		return mt.findAll(VehicleType.class, env.getProperty("vehicle-collection-name"));

	}

}
