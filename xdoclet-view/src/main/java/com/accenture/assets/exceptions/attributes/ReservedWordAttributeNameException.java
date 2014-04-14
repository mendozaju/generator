package com.accenture.assets.exceptions.attributes;

public class ReservedWordAttributeNameException extends AtributoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public ReservedWordAttributeNameException(String nombre) {
		super();
//		error = "El nombre del atributo "+nombre+" es una palabra reservada de JAVA";
		error = "The attribute name ["+nombre+"] is a JAVA reserved word";
	}

}
