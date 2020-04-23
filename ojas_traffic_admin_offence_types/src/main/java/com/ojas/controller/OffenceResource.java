package com.ojas.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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

import com.ojas.exceptions.EmptyNameField;
import com.ojas.exceptions.ZeroSizeException;
import com.ojas.model.OffenceType;
import com.ojas.service.OffenceServiceImpl;

@RestController
public class OffenceResource {

	@Autowired
	private OffenceServiceImpl service;

	@PostMapping("/save")
	public ResponseEntity<Object> saveOffence(@RequestBody OffenceType offence) {
		OffenceType off = null;
		try {
			if (offence.getOffences().trim().isEmpty() || offence == null)
				throw new EmptyNameField("offence is null");
			off = service.saveOffence(offence);
			return new ResponseEntity<Object>(off, HttpStatus.OK);

		} catch (NullPointerException e) {
			return new ResponseEntity<Object>("One of the fiels is not inserted", HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (EmptyNameField e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			return new ResponseEntity<Object>("blank", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Object> editOffence(@PathVariable("id") String id, @PathParam("offence") String offence) {
		OffenceType edit = null;
		try {
			if (offence == null || offence.trim().isEmpty())
				throw new EmptyNameField("offence is null");
			edit = service.editOffence(id, offence);
			return new ResponseEntity<Object>(edit, HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<Object>("One of the fiels is not inserted", HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (EmptyNameField e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			return new ResponseEntity<Object>("blank", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity<Object> deleteOffence(@PathVariable("id") String id) {
		String deloff = null;
		try {
			if (id == null)
				throw new EmptyNameField("id is null");
			deloff = service.deleteOffence(id);
			return new ResponseEntity<Object>(deloff, HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<Object>("One of the fiels is not inserted", HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (EmptyNameField e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			return new ResponseEntity<Object>("blank", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll() {
		List<OffenceType> list = service.getAll();
		try {
			if (list == null)
				throw new NullPointerException();
			if (list.isEmpty())
				throw new ZeroSizeException("No records found");
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<Object>("One of the fiels is not inserted", HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (ZeroSizeException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Blank", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
