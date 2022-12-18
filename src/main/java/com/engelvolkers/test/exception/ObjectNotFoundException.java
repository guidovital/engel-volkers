package com.engelvolkers.test.exception;

/*
* Exception for object not found
* 
* @author Guilherme Vital
*/
public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
