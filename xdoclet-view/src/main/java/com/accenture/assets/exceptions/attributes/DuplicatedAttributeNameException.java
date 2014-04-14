package com.accenture.assets.exceptions.attributes;

public class DuplicatedAttributeNameException extends AtributoException {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	/**
		 * 
		 */

	public DuplicatedAttributeNameException(String nombre) {
		super();
		error = "Attribute ["+nombre+"] repeated";
	}

}
