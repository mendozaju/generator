package com.accenture.assets.exceptions.attributes;

public class EmptyAttributeTypeException extends AtributoException {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	/**
		 * 
		 */

	public EmptyAttributeTypeException(String nombre) {
		super();
//		error = "Attribute "+nombre+" no tiene tipo de dato definido";
		error = "Attribute ["+nombre+"] doesn't have a type";
	}

}
