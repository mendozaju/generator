package com.accenture.assets.exceptions.classes;

public class SpacesInClassNameException extends ClaseException{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	public SpacesInClassNameException(){
		super();            
//		error = "El nombre de la clase no puede contener espacios";
		error = "The class name must not contain spaces";
	}
}
