package com.ojas.exceptions;

public class EmptyFieldFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyFieldFoundException(String message) {
		super(message);
	}
}
