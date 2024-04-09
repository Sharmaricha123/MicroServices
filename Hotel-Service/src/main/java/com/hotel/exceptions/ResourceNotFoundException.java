package com.hotel.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException() {
		super();
		
	}

	public ResourceNotFoundException(String message) {
		super("Resource not found");
		
	}
	
	

}
