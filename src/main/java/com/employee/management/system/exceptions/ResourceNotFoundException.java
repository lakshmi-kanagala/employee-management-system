package com.employee.management.system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * CustomException class to consume exceptions when data not found
 * @author Lakshmi K
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		this("EntityRepresentationModel not found");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
