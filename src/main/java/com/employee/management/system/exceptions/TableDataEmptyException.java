package com.employee.management.system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * custom exception indicates the user about table content is empty
 * @author Lakshmi K
 *
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class TableDataEmptyException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TableDataEmptyException(String message) {
		super(message);
	}


}
