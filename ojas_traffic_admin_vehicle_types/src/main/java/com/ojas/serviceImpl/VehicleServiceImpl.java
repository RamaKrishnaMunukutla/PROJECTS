package com.ojas.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.model.VehicleType;
import com.ojas.repoImpl.VehicleRepoImpl;
import com.ojas.request.VehicleTypesRequest;
import com.ojas.response.VehicleTypeResponse;
import com.ojas.service.VehicleService;
import com.ojas.util.Random_IdGenerator;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepoImpl repo;
	
	private VehicleTypeResponse vehicleTypeResponse;

	@Override
	public ResponseEntity<Object> savevehicleTypesRequest(VehicleTypesRequest vehicleTypesRequest) {

		vehicleTypesRequest.getVehicleTypes().get(0).setId(Random_IdGenerator.getVehicleID());

		VehicleType saveVehicle = repo.saveVehicle(vehicleTypesRequest);

		List<VehicleType> vehicleTypes = vehicleTypesRequest.getVehicleTypes();

		if (!(saveVehicle.getVehicleTypes().isEmpty())) {
			
			vehicleTypeResponse = new VehicleTypeResponse();
			vehicleTypeResponse.setStatus(200);
			vehicleTypeResponse.setVehicleType(vehicleTypes);		
			vehicleTypeResponse.setMessage("successfully save the vehicleType");
			
			return new ResponseEntity<Object>(vehicleTypeResponse, HttpStatus.OK);
		} else {
			
			vehicleTypeResponse.setStatus(422);
			vehicleTypeResponse.setVehicleType(vehicleTypes);
			vehicleTypeResponse.setMessage("fail to save the vehicleType");
			
			return new ResponseEntity<Object>(vehicleTypeResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	@Override
	public ResponseEntity<Object> editVehicle(VehicleTypesRequest vehicleTypesRequest, String id) {
		
		List<VehicleType> vehicleTypes = vehicleTypesRequest.getVehicleTypes();
		vehicleTypes.get(0).setId(id);
		VehicleType editVehicle = repo.editVehicle(vehicleTypesRequest, id);
		
		if (editVehicle == null) {
			
			vehicleTypeResponse = new VehicleTypeResponse();
			vehicleTypeResponse.setStatus(422);
			vehicleTypeResponse.setMessage("fail to update vehicleType");
			
			return new ResponseEntity<Object>(vehicleTypeResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		
		} else {

			vehicleTypeResponse = new VehicleTypeResponse();
			vehicleTypeResponse.setStatus(200);
			vehicleTypeResponse.setVehicleType(vehicleTypes);
			vehicleTypeResponse.setMessage("successfully update the vehicleType");
		
			return new ResponseEntity<Object>(vehicleTypeResponse, HttpStatus.OK);
		}

	}

	@Override
	public ResponseEntity<Object> deleteVehicleById(String id) {

		boolean deleteVehicle = repo.deleteVehicle(id);
		
		if (deleteVehicle) {
			
			vehicleTypeResponse = new VehicleTypeResponse();
			vehicleTypeResponse.setStatus(200);
			vehicleTypeResponse.setMessage("successfully delete the vehicleType");
			
			return new ResponseEntity<Object>(vehicleTypeResponse, HttpStatus.OK);
		} else {
			
			vehicleTypeResponse = new VehicleTypeResponse();
			vehicleTypeResponse.setMessage("fail to delete vehicleType");
			vehicleTypeResponse.setStatus(422);
			
			return new ResponseEntity<Object>(vehicleTypeResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	@Override
	public ResponseEntity<Object> getAllVehicle() {

		List<VehicleType> all = repo.getAll();
		
		if (all.isEmpty()) {
			
			vehicleTypeResponse = new VehicleTypeResponse();
			vehicleTypeResponse.setMessage("fail to get All vehicles");
			vehicleTypeResponse.setStatus(422);
			
			return new ResponseEntity<Object>(vehicleTypeResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			
			vehicleTypeResponse = new VehicleTypeResponse();
			vehicleTypeResponse.setStatus(200);
			vehicleTypeResponse.setVehicleType(all);
			vehicleTypeResponse.setMessage("successfully get All the vehicles");
			
			return new ResponseEntity<Object>(vehicleTypeResponse, HttpStatus.OK);
		}

	}

}
