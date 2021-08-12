package com.mercadolibre.mutantservice.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.mercadolibre.mutantservice.exceptions.BasesNitrogenadasException;

public class MutantUtil {

	
	public static boolean isMutant(List<String> dna) throws BasesNitrogenadasException {
		int dnaSize = dna.size();
		char [][] dnaTable = new char [dnaSize][dnaSize];
		boolean coincideSecuenciaHorizontal = false;
		StringBuilder secuenciaHorizontal;
		for (int i=0;i<dnaSize;i++) {
			secuenciaHorizontal = new StringBuilder(dna.get(i));
			
			coincideSecuenciaHorizontal = evaluarSecuencia(secuenciaHorizontal);
			if(!coincideSecuenciaHorizontal) {
				int secuenceSize= secuenciaHorizontal.length();
				char [] secuenciaArray =new char[secuenceSize];
				secuenciaHorizontal.getChars(0, secuenceSize, secuenciaArray, 0);
				dnaTable[i] = secuenciaArray;
			} else {
				return coincideSecuenciaHorizontal;
			}
		}
		return evaluarSecuenciaVerticalYOblicua(dnaTable);
	}
	
	private static boolean evaluarSecuenciaVerticalYOblicua(char [][] dnaTable) {
		int dnaSize = dnaTable.length;
		boolean esMutante;
		StringBuilder mutanteVertical;
		StringBuilder mutanteOblicuo;

		for(int i=0;i<dnaSize;i++) {
			mutanteOblicuo = new StringBuilder("");
			mutanteVertical = new StringBuilder("");
			for(int j=0;j<dnaSize;j++) {
				char baseNitrogenadaVertical = dnaTable[j][i];
				char baseNitrogenadaOblicua = dnaTable[j][j];
				mutanteOblicuo.append(baseNitrogenadaOblicua);
				mutanteVertical.append(baseNitrogenadaVertical);
				System.out.println("mutanteVertical "+ mutanteVertical);
				System.out.println("mutanteOblicuo "+ mutanteOblicuo);
			}
			esMutante = evaluarSecuencia(mutanteVertical);
			if(esMutante) {
				return esMutante;
			}
			esMutante = evaluarSecuencia(mutanteOblicuo);
			if(esMutante) {
				return esMutante;
			}
		}
		return false;
	}
	
	private static boolean evaluarSecuencia(StringBuilder secuencia) {
		if(secuencia.indexOf("AAAA")!=-1 ||secuencia.indexOf("TTTT")!=-1
				|| secuencia.indexOf("CCCC")!=-1 ||secuencia.indexOf("GGGG")!=-1) {
			return true;
		}
		return false;
	}
	
	public static void validarBasesNitrogenadas(List<String> dnaList) throws BasesNitrogenadasException {
		String expRegular = "^[ATGC]+$";
        Pattern pat = Pattern.compile(expRegular);
        boolean basesNitrogenadasValidas = dnaList.stream().map( x -> x.toUpperCase())
                .filter(x-> !pat.matcher(x).matches()).collect(Collectors.toList()).isEmpty();
        if(!basesNitrogenadasValidas) {
			throw new BasesNitrogenadasException("Se han ingresado caracteres no válidos en la cadena nitrogenada. Los caracteres permitidos son A, T, G Y C.");
		}
	}
	
}
