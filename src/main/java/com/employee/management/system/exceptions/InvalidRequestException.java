package com.employee.management.system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException{
	public InvalidRequestException(String message) {
		super(message);
	}

}
