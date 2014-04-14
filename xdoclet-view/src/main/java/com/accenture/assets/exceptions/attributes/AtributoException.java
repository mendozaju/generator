package com.accenture.assets.exceptions.attributes;

public class AtributoException extends Exception{
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
