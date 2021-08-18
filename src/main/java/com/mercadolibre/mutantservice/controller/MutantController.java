package com.mercadolibre.mutantservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mercadolibre.mutantservice.exceptions.BasesNitrogenadasException;
import com.mercadolibre.mutantservice.model.DNAModel;
import com.mercadolibre.mutantservice.model.DNASequence;
import com.mercadolibre.mutantservice.model.StatsModel;
import com.mercadolibre.mutantservice.service.DNAService;
import com.mercadolibre.mutantservice.util.MutantUtil;

@RestController
public class MutantController {

	@Autowired
	private DNAService service;

	@PostMapping("/mutant")
	public ResponseEntity<HttpStatus> save(@RequestBody DNASequence dnasequence) throws BasesNitrogenadasException {
		
		List<String> dnaList = new ArrayList<String>();
		dnaList.addAll(dnasequence.getDna());
		
		MutantUtil.validarBasesNitrogenadas(dnaList);
		
		HttpStatus status;
		status = MutantUtil.isMutant(dnaList);
		
		DNAModel dnaModel = new DNAModel();
		dnaModel.setDna(dnasequence.getDna().toString());
		boolean isMutant = status.equals(HttpStatus.OK);
		dnaModel.setMutant(isMutant);
		
		service.save(dnaModel);
		
		ResponseEntity<HttpStatus> response = ResponseEntity.status(status).build();
		return response;
//		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	
	@GetMapping("/stats")
	public ResponseEntity<StatsModel> stats() {
		StatsModel stats = service.calculateStats();
		
		return new ResponseEntity<StatsModel>(stats,HttpStatus.OK);
	}
	
	
	
}
