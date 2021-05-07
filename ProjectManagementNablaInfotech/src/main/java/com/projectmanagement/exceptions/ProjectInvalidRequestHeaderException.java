package com.projectmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectInvalidRequestHeaderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7392429021032768616L;

	public ProjectInvalidRequestHeaderException(String message) {
		super(message);
		
	}
	
	

}
