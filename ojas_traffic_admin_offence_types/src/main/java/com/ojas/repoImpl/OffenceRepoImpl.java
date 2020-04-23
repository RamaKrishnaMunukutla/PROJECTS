package com.ojas.repoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.ojas.exceptions.ZeroSizeException;
import com.ojas.model.OffenceType;
import com.ojas.repo.OffenceRepo;
import com.ojas.request.OffenceRequest;

@Repository
public class OffenceRepoImpl implements OffenceRepo {

	@Autowired
	private MongoTemplate mt;

	@Autowired
	private Environment env;

	public OffenceType saveOffence(OffenceRequest offenceRequest) throws NullPointerException {
		List<OffenceType> offenceType = offenceRequest.getOffenceType();
		OffenceType result = null;
		for(OffenceType offence : offenceType) {			
			 result = mt.insert(offence, env.getProperty("offence-collection-name"));
		}
		return result;
	}

	public OffenceType editOffence(String id, OffenceRequest offence) throws NullPointerException {
		List<OffenceType> offenceType = offence.getOffenceType();
		OffenceType obj = mt.findById(id, OffenceType.class, env.getProperty("offence-collection-name"));
		if (obj == null)
			throw new NullPointerException();
		String offences = offenceType.get(0).getOffences();
		obj.setId(id);
		obj.setOffences(offences);
		OffenceType result = mt.save(obj, env.getProperty("offence-collection-name"));
		return result;
	}

	public boolean deleteOffence(String id) throws NullPointerException {
		OffenceType obj = mt.findById(id, OffenceType.class, env.getProperty("offence-collection-name"));
		if (obj == null)
			throw new NullPointerException();
		if (mt.remove(obj, env.getProperty("offence-collection-name")) != null)
			return true;
		else
			return false;
	}

	public List<OffenceType> getAll() throws NullPointerException, ZeroSizeException {
		List<OffenceType> all = mt.findAll(OffenceType.class, env.getProperty("offence-collection-name"));
		return all;
	}

}
