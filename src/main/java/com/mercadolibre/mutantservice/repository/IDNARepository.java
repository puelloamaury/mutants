package com.mercadolibre.mutantservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mercadolibre.mutantservice.model.DNAModel;
import com.mercadolibre.mutantservice.model.StatsModel;

public interface IDNARepository extends JpaRepository<DNAModel, Integer>{
	
	@Query(value = "SELECT new com.mercadolibre.mutantservice.model.StatsModel(count(u) as mutantCount, (SELECT count(t2) from DNAModel t2 WHERE t2.isMutant = false) as humanCount) FROM DNAModel u WHERE u.isMutant = true")
	public StatsModel calculateStats();

}
