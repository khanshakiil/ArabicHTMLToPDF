package com.hospital.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = { "stackTrace", "cause", "localizedMessage", "message", "suppressed" })
public class InvalidInput extends Exception {

	public InvalidInput(String message) {
		super(message);
		this.errorMessage = message;

	}

	public InvalidInput() {
		super();
	}

	private String errorMessage;
}
