package com.projectmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7815938454899479404L;

	public ProjectNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	

	
}
