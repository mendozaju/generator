package com.accenture.assets.exceptions;

/**
 * @author adrian.musante
 *
 */
//TODO: Check for business name
public class AppException extends Exception {

	private static final long serialVersionUID = 2040290277214204614L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}
	
}
