package com.cursojunit5.cursojunit5.barriga.domain.exceptions;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
		super(message);
	}
	
}
