package com.capgemini.exception;

public class MyIllegalArgumentException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyIllegalArgumentException(int id) {
		super(String.format("Invalid parameter was entered", id));
	}

}
