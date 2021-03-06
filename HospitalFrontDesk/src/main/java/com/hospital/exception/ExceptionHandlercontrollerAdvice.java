package com.hospital.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlercontrollerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidInput.class)
	public @ResponseBody ResponseEntity  handleResourceNotFound(final InvalidInput exception, WebRequest request) {

		InvalidInput error = new InvalidInput();
		error.setErrorMessage(exception.getMessage());
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody InvalidInput handleException(final Exception exception) {

		InvalidInput error = new InvalidInput();
		error.setErrorMessage(exception.getMessage());

		return error;
	}

}
