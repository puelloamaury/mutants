package com.mercadolibre.mutantservice;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercadolibre.mutantservice.controller.MutantController;
import com.mercadolibre.mutantservice.exceptions.BasesNitrogenadasException;
import com.mercadolibre.mutantservice.model.DNASequence;
import com.mercadolibre.mutantservice.model.StatsModel;

@RunWith(SpringRunner.class)
@SpringBootTest
class MutantServiceApplicationTests {

	@Autowired
	private MutantController controller;
	
	@Test
	void contextLoads() {
		Assert.assertNotNull(controller);
	}
	
	
	@Test
	public void saveMutant() throws BasesNitrogenadasException {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		List<String> dnaList = Arrays.asList(dna);
		DNASequence sequence = new DNASequence(); 
		sequence.setDna(dnaList);
		ResponseEntity<HttpStatus> response = controller.save(sequence);
		
		HttpStatus status = HttpStatus.OK;
		ResponseEntity.status(status).build();
		ResponseEntity<HttpStatus> responseExpected = ResponseEntity.status(status).build();
		
		Assert.assertEquals(responseExpected, response);
	}
	
	@Test
	public void saveHuman() throws BasesNitrogenadasException {
		String[] dna = {"ATGCGA","ACGTGC","TTATGT","AGAATG","CCCTTA","TCACTG"};
		List<String> dnaList = Arrays.asList(dna);
		DNASequence sequence = new DNASequence(); 
		sequence.setDna(dnaList);
		ResponseEntity<HttpStatus> response = controller.save(sequence);
		
		HttpStatus status = HttpStatus.FORBIDDEN;
		ResponseEntity.status(status).build();
		ResponseEntity<HttpStatus> responseExpected = ResponseEntity.status(status).build();
		
		Assert.assertEquals(responseExpected, response);
	}
	
	@Test
	public void getStats() {
		ResponseEntity<StatsModel> response = controller.stats();
		StatsModel statsExpected = new StatsModel(1, 1);
		ResponseEntity<StatsModel> responseExpected = new ResponseEntity<StatsModel>(statsExpected,HttpStatus.OK);
		Assert.assertEquals(responseExpected, response);
		
	}

}
