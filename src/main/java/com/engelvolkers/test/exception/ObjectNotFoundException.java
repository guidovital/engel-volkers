package com.engelvolkers.test.exception;

@SuppressWarnings("serial")
public class ObjectNotFoundException extends RuntimeException {
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
