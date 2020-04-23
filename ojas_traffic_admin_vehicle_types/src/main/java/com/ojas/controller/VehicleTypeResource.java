package com.ojas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.model.ErrorResponse;
import com.ojas.request.VehicleTypesRequest;
import com.ojas.serviceImpl.VehicleServiceImpl;

@RestController
public class VehicleTypeResource {

	@Autowired
	private VehicleServiceImpl service;

	private static final String MESSAGE = "Request object is null";

	private static ErrorResponse error = null;

	@PostMapping("/save")
	public ResponseEntity<Object> saveVehicle(@RequestBody VehicleTypesRequest vehicleTypesRequest) {

		try {

			if (vehicleTypesRequest.getVehicleTypes().get(0).getVehicleTypes().isEmpty()
					|| vehicleTypesRequest == null) {
				error = new ErrorResponse();
				error.setMessage(MESSAGE);
				error.setStatus(422);
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			return service.savevehicleTypesRequest(vehicleTypesRequest);

		} catch (Exception e) {
			error = new ErrorResponse();
			error.setStatus(409);
			error.setMessage(e.getMessage());

			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Object> editVehicle(@PathVariable("id") String id,
			@RequestBody VehicleTypesRequest vehicleTypesRequest) {

		try {

			if (vehicleTypesRequest == null
					|| vehicleTypesRequest.getVehicleTypes().get(0).getVehicleTypes().trim().isEmpty()) {
				error = new ErrorResponse();
				error.setStatus(422);
				error.setMessage(MESSAGE);

				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (id == null || id.trim().isEmpty()) {

				error = new ErrorResponse();
				error.setStatus(422);
				error.setMessage(MESSAGE);

				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			ResponseEntity<Object> editVehicle = service.editVehicle(vehicleTypesRequest, id);

			return editVehicle;
		} catch (Exception e) {
			error = new ErrorResponse();
			error.setStatus(409);
			error.setMessage(e.getMessage());

			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity<Object> deleteVehicle(@PathVariable("id") String id) {

		try {
			if (id == null || id.trim().isEmpty()) {

				error = new ErrorResponse();
				error.setStatus(422);
				error.setMessage(MESSAGE);

				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			return service.deleteVehicleById(id);

		} catch (NullPointerException e) {
			error = new ErrorResponse();
			error.setStatus(409);
			error.setMessage("One of the fields is not inserted");

			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);

		} catch (Exception e) {

			error = new ErrorResponse();
			error.setStatus(409);
			error.setMessage(e.getMessage());

			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll() {

		try {
			return service.getAllVehicle();

		} catch (Exception e) {
			error = new ErrorResponse();
			error.setStatus(409);
			error.setMessage(e.getMessage());

			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		}
	}

}
