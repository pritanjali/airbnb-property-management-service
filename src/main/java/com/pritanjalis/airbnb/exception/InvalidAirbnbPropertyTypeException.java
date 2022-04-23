package com.pritanjalis.airbnb.exception;

public class InvalidAirbnbPropertyTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidAirbnbPropertyTypeException(){
		super();
	}
	
	public InvalidAirbnbPropertyTypeException(String errorMessage){
		super(errorMessage);
	}
}
