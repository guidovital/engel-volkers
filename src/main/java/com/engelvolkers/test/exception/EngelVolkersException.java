package com.engelvolkers.test.exception;

/*
 * Base exception for Engel & Volkers application
 * 
 * @author Guilherme Vital
 */
public class EngelVolkersException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EngelVolkersException(String message) {
        super(message);
    }

    public EngelVolkersException(String message, Throwable cause) {
        super(message, cause);
    }
}
