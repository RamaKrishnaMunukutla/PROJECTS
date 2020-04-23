package com.ojas.exceptions;

public class EmptyNameField extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyNameField(String message) {
		super(message);
	}
}
