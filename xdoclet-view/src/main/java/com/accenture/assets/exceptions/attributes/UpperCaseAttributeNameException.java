package com.accenture.assets.exceptions.attributes;

public class UpperCaseAttributeNameException extends AtributoException{
	/**
	 * 
	 */
private static final long serialVersionUID = 1L;

/**
	 * 
	 */

public UpperCaseAttributeNameException(String nombre) {
	super();
//	error = "El atributo "+nombre+" comienza con mayuscula";
	error = "Attribute ["+nombre+"] start whith a capital letter";
}

}
