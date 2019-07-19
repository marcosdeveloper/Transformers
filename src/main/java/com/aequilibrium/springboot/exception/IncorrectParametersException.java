package com.aequilibrium.springboot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class IncorrectParametersException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5377412683548036149L;

	public IncorrectParametersException(String message) {
		super(message);
	}
}