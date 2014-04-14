package com.accenture.assets.exceptions;

/**
 * @author adrian.musante
 *
 */
//TODO: Check for business name
public class AppRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 4473540489096496077L;

	public AppRuntimeException() {
		super();
	}

	public AppRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppRuntimeException(String message) {
		super(message);
	}

	public AppRuntimeException(Throwable cause) {
		super(cause);
	}
	
}
