package com.accenture.assets.exceptions.attributes;

public class SpacesInAttributeNameException extends AtributoException{
	/**
	 * 
	 */
private static final long serialVersionUID = 1L;

/**
	 * 
	 */

public SpacesInAttributeNameException(String nombre) {
	super();
//	error = "El atributo "+nombre+" contiene espacios en su nombre";
	error = "Attribute ["+nombre+"] contains empty spaces in its name";
}

}
