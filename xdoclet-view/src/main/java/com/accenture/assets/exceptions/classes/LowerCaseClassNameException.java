package com.accenture.assets.exceptions.classes;

public class LowerCaseClassNameException extends ClaseException {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public LowerCaseClassNameException() {
		super();
//		error = "El nombre de la clase no puede comenzar con minuscula";
		error = "The class name cant start with a capital letter";
	}
}
