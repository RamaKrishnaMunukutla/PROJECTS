package com.ojas.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.exceptions.ZeroSizeException;
import com.ojas.model.OffenceType;
import com.ojas.repoImpl.OffenceRepoImpl;
import com.ojas.request.OffenceRequest;
import com.ojas.response.OffenceResponse;
import com.ojas.service.OffenceService;
import com.ojas.util.Random_IdGenerator;

@Service
public class OffenceServiceImpl implements OffenceService {

	@Autowired
	private OffenceRepoImpl repo;

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	OffenceResponse response = null;

	public ResponseEntity<Object> saveOffence(OffenceRequest offenceRequest) throws NullPointerException {
		offenceRequest.getOffenceType().get(0).setId(Random_IdGenerator.getOffenceID());
		OffenceType saveOffence = repo.saveOffence(offenceRequest);
		List<OffenceType> offenceType = offenceRequest.getOffenceType();
		if (saveOffence != null) {
			logger.debug("save method");
			response = new OffenceResponse();
			response.setMessage("successfully save the offenceType");
			response.setStatus(200);
			response.setOffence(offenceType);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.setOffence(offenceType);
			response.setMessage("fail to save the offenceType");
			response.setStatus(422);
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public ResponseEntity<Object> editOffence(String id, OffenceRequest offence) throws NullPointerException {
		OffenceResponse response = null;
		List<OffenceType> offenceType = offence.getOffenceType();
		offenceType.get(0).setId(id);
		OffenceType editOffence = repo.editOffence(id, offence);
		if (editOffence == null) {
			response = new OffenceResponse();
			response.setOffence(offenceType);
			response.setStatus(422);
			response.setMessage("fail to update offenceType");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			logger.debug("update method");
			response = new OffenceResponse();
			response.setStatus(200);
			response.setOffence(offenceType);
			response.setMessage("successfully update the offenceType");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> deleteOffence(String id) throws NullPointerException {
		OffenceResponse response = null;
		boolean deleted = repo.deleteOffence(id);
		if (deleted == true) {
			logger.debug("delete method");
			response = new OffenceResponse();
			response.setStatus(200);
			response.setMessage("sucessfully deleted");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response = new OffenceResponse();
			response.setStatus(422);
			response.setMessage("fail to delete");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public ResponseEntity<Object> getAll() throws NullPointerException, ZeroSizeException {
		OffenceResponse response = null;
		List<OffenceType> all = repo.getAll();
		if (all.isEmpty() || all == null) {
			response = new OffenceResponse();
			response.setStatus(422);
			response.setMessage("list is empty");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			response = new OffenceResponse();
			response.setOffence(all);
			response.setStatus(200);
			response.setMessage("success");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}

	}

}
