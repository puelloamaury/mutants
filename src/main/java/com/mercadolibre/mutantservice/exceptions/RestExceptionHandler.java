package com.mercadolibre.mutantservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.mercadolibre.mutantservice.model.Message;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String ERROR_CONTACTE_AL_ADMINISTRADOR = "Ourrio un error, contacte con el administrador!";
	
	@ExceptionHandler
	public final ResponseEntity<Message> manejarExcepciones(Exception e) {
		Message mensaje;
		if(e instanceof BasesNitrogenadasException) {
			mensaje = new Message(e.getMessage());
			
		} else {
			mensaje = new Message(ERROR_CONTACTE_AL_ADMINISTRADOR);
		}
		e.printStackTrace();
		return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}