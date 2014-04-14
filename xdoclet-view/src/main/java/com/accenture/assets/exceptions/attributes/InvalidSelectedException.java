package com.accenture.assets.exceptions.attributes;

public class InvalidSelectedException extends AtributoException{

	private static final long serialVersionUID = 1L;

	public InvalidSelectedException(String nombre) {
		super();
//		error = "Atributo [" + nombre + "]: No se le puede asignar funciones del tipo: id/search/create/modify/delete.";
		error = "Attribute [" + nombre + "]: is not eligible for \"id/search/create/modify/delete\" functions.";
	}
}
