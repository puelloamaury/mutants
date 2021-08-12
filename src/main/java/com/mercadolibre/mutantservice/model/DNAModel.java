package com.mercadolibre.mutantservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DNAModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="dna",unique = true)
	private String dna;
	@Column(name="is_mutant")
	private boolean isMutant;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDna() {
		return dna;
	}
	public void setDna(String dna) {
		this.dna = dna;
	}
	public boolean isMutant() {
		return isMutant;
	}
	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
	
	
	
}
