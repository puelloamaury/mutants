package com.mercadolibre.mutantservice.model;

import java.util.List;

public class DNASequence {

	
	private List<String> dna;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}

	@Override
	public String toString() {
		return dna.toString();
	}
	
	
	
}
