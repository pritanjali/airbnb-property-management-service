package com.pritanjalis.airbnb.domain;

public class NoEntityDataFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 8710686984615009559L;

	public NoEntityDataFoundException(final String entityName) {
		super(String.format("No data found for %.", entityName));
	}

}
