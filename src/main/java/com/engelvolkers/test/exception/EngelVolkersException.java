package com.engelvolkers.test.exception;

@SuppressWarnings("serial")
public class EngelVolkersException extends RuntimeException {
	
	public EngelVolkersException(String message) {
		super(message);
	}

	public EngelVolkersException(String message, Throwable cause) {
		super(message, cause);
	}
}
