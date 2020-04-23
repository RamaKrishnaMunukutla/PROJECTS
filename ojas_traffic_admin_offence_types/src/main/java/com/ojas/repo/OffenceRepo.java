package com.ojas.repo;

import java.util.List;

import com.ojas.exceptions.ZeroSizeException;
import com.ojas.model.OffenceType;
import com.ojas.request.OffenceRequest;

public interface OffenceRepo {
	public OffenceType saveOffence(OffenceRequest offenceRequest) throws NullPointerException;

	public OffenceType editOffence(String id, OffenceRequest offence) throws NullPointerException;

	public boolean deleteOffence(String id) throws NullPointerException;

	public List<OffenceType> getAll() throws NullPointerException, ZeroSizeException;

}
