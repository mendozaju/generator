package com.accenture.assets.exceptions.classes;

public class EmptyClassNameException extends ClaseException {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public EmptyClassNameException() {
		super();
//		error = "No puede existir clase con nombre vacio";
		error = "Class name can't be empty";
	}
}
