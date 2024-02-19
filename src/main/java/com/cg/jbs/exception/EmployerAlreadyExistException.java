package com.cg.jbs.exception;

public class EmployerAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 */
	public EmployerAlreadyExistException(final String message) {
		super(message);
	}

}
