package com.accenture.assets.exceptions.attributes;

public class EmptyAttributeNameException extends AtributoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public EmptyAttributeNameException() {
		super();
//		error = "No puede existir nombre de atributo vacio";
		error = "Attribute must have a name";
		
		
	}

}
