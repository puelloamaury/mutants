package com.mercadolibre.mutantservice.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import com.mercadolibre.mutantservice.exceptions.BasesNitrogenadasException;

public class MutantUtil {

	public static HttpStatus isMutant(List<String> dna) throws BasesNitrogenadasException {
		int dnaSize = dna.size();
		char[][] dnaTable = new char[dnaSize][dnaSize];
		Map<Integer, String> secuenciasVerticales = new HashMap<Integer, String>();
		for (int i = 0; i < dnaSize; i++) {
			String secuenciaHorizontal = dna.get(i);
			
			for(int j = 0; j<secuenciaHorizontal.length();j++) {
				String valorActual;
				valorActual = secuenciasVerticales.get(j) != null ? secuenciasVerticales.get(j) : "";
				valorActual = valorActual + secuenciaHorizontal.charAt(j);
				
				secuenciasVerticales.put(j, valorActual);
			}
			int secuenceSize = secuenciaHorizontal.length();
			char[] secuenciaArray = new char[secuenceSize];
			secuenciaHorizontal.getChars(0, secuenceSize, secuenciaArray, 0);
			dnaTable[i] = secuenciaArray;
		}
		dna.addAll(secuenciasVerticales.values());
		return enccontrarSecuenciaOblicua(dnaTable, dna);
	}

	private static HttpStatus enccontrarSecuenciaOblicua(char[][] dnaTable, List<String> secuencias) {
		int dnaSize = dnaTable.length;

		int columna =0;
		int inicio = 0;
		StringBuilder mutanteOblicuoDerechaIzquierda;
		mutanteOblicuoDerechaIzquierda = new StringBuilder("");
		StringBuilder mutanteOblicuoIzquierdaDerecha;
		mutanteOblicuoIzquierdaDerecha = new StringBuilder();
		for (int fila = 0; inicio < dnaSize;) {
			char baseNitrogenadaOblicua = dnaTable[fila][columna];
			mutanteOblicuoIzquierdaDerecha.append(baseNitrogenadaOblicua);
			
			columna++;
			fila++;
			if(fila == dnaSize) {
				System.out.println("mutanteOblicuoIzquierdaDerecha " + mutanteOblicuoIzquierdaDerecha);
				if(mutanteOblicuoIzquierdaDerecha.length() > 3) {
					secuencias.add(mutanteOblicuoIzquierdaDerecha.toString());
				} else {
					break;
				}
				mutanteOblicuoIzquierdaDerecha = new StringBuilder();
				inicio++;
				fila = inicio;
				columna = 0;
			}
		}
		columna = dnaSize-1;
		inicio = 0;
		for (int fila = inicio; inicio < dnaSize && columna >= 0 ;) {
			char baseNitrogenadaOblicua = dnaTable[fila][columna];
			mutanteOblicuoDerechaIzquierda.append(baseNitrogenadaOblicua);
			
			columna--;
			fila++;
			if(fila == dnaSize) {
				System.out.println("mutanteOblicuoDerechaIzquierda " + mutanteOblicuoDerechaIzquierda);
				if(mutanteOblicuoDerechaIzquierda.length() > 3) {
					secuencias.add(mutanteOblicuoDerechaIzquierda.toString());
				} else {
					break;
				}
				mutanteOblicuoDerechaIzquierda = new StringBuilder();
				inicio++;
				fila = inicio;
				columna = dnaSize-1;
			}
		}
			
		
		return evaluarSecuencia(secuencias);
	}

	private static HttpStatus evaluarSecuencia(List<String> secuencias) {
		String regExp = "(.)\1{3}+";
		Pattern pat = Pattern.compile(regExp);
		
		boolean esHumano = secuencias.stream().map(x -> x.toUpperCase())
				.filter(x -> x.contains("AAAA") || x.contains("CCCC") 
						|| x.contains("TTTT") || x.contains("GGGG"))
				.collect(Collectors.toList())
				.isEmpty();
		//boolean coincide = secuencia.matches(regExp);
		if(esHumano) {
			return HttpStatus.FORBIDDEN;
		}
		return HttpStatus.OK;
	}

	public static void validarBasesNitrogenadas(List<String> dnaList) throws BasesNitrogenadasException {
		String expRegular = "^[ATGC]+$";
		Pattern pat = Pattern.compile(expRegular);
		boolean basesNitrogenadasValidas = dnaList.stream().map(x -> x.toUpperCase())
				.filter(x -> !pat.matcher(x).matches()).collect(Collectors.toList()).isEmpty();
		if (!basesNitrogenadasValidas) {
			throw new BasesNitrogenadasException(
					"Se han ingresado caracteres no válidos en la cadena nitrogenada. Los caracteres permitidos son A, T, G Y C.");
		}
	}

}
