package com.pritanjalis.airbnb.domain;

public class EntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException() {
		super();
	}
	
	public EntityNotFoundException(final String entityName,  final String keyName, final String keyValue) {
		super(String.format("%s with %s %s not found", entityName, keyName, keyValue));
	}
	
	public EntityNotFoundException(final String errorMessage) {
		super(errorMessage);
	}

}
