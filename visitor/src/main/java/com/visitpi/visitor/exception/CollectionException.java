package com.visitpi.visitor.exception;

public class CollectionException extends Exception{

	
	
	private static final long serialVersionUID = 1L;

	public CollectionException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Todo with "+id+" not found!";
	}
	
	public static String UserAlreadyExists() {
		return "Todo with given name already exists";
	}

}
