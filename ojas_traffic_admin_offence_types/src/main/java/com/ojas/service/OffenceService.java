package com.ojas.service;

import org.springframework.http.ResponseEntity;

import com.ojas.exceptions.ZeroSizeException;
import com.ojas.request.OffenceRequest;

public interface OffenceService {
	public ResponseEntity<Object> saveOffence(OffenceRequest offenceRequest) throws NullPointerException;

	public ResponseEntity<Object> editOffence(String id, OffenceRequest offence) throws NullPointerException;

	public ResponseEntity<Object> deleteOffence(String id) throws NullPointerException;

	public ResponseEntity<Object> getAll() throws NullPointerException, ZeroSizeException;

}
