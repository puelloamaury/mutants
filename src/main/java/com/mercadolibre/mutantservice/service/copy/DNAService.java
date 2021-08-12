package com.mercadolibre.mutantservice.service.copy;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.mercadolibre.mutantservice.model.DNAModel;
import com.mercadolibre.mutantservice.model.StatsModel;
import com.mercadolibre.mutantservice.repository.IDNARepository;

public class DNAService {

	@Autowired
	private IDNARepository repository;
	
	
	public void save(DNAModel entity) {
		repository.save(entity);
		
	}
	
	public StatsModel calculateStats() {
		return repository.calculateStats();
	}
	
}
