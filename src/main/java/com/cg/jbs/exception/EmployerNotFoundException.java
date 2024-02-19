package com.cg.jbs.exception;

public class EmployerNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 */
	public EmployerNotFoundException(final String message) {
		super(message);
	}
}
