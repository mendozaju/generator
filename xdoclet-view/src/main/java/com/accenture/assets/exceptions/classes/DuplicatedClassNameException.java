package com.accenture.assets.exceptions.classes;


public class DuplicatedClassNameException extends ClaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	public DuplicatedClassNameException(){
		super();            
//		error = "Clase ya existente en el proyecto";
		error = "There is already a class with the same name in the project";
	}

	
}

