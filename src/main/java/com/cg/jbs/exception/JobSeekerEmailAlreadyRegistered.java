package com.cg.jbs.exception;

public class JobSeekerEmailAlreadyRegistered extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * @param message -- contains message
	 */
	public JobSeekerEmailAlreadyRegistered(final String message) {
		super(message);
	}
	
	

}
