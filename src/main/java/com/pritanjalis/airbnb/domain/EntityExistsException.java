package com.pritanjalis.airbnb.domain;

public class EntityExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityExistsException(final String entity, final String keyName, final String keyValue) {
		super(String.format("Not able to create %s with %s  %s exists", entity, keyName, keyValue));
	}

}
