package com.mercadolibre.mutantservice.service;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.mercadolibre.mutantservice.model.DNAModel;
import com.mercadolibre.mutantservice.model.StatsModel;
import com.mercadolibre.mutantservice.repository.IDNARepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;

@Configuration
public class DNAService {

	@Autowired
	private IDNARepository repository;
	
	public void save(DNAModel entity) {
		try {
			repository.save(entity);
		} catch(DataIntegrityViolationException e) {
			System.out.println("El elemento ya existe");
		}
	}
	
	public StatsModel calculateStats() {
		return repository.calculateStats();
	}
	
}
