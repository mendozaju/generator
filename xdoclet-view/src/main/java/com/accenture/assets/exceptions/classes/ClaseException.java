package com.accenture.assets.exceptions.classes;

public class ClaseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String error;
	
	public String getMessage()
	{
		return error;
	}
}
