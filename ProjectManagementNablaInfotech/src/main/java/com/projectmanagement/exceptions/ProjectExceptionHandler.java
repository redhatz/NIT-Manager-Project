package com.projectmanagement.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.projectmanagement.model.ApiErrorResponse;
import com.projectmanagement.exceptions.*;
import com.projectmanagement.api.ApiException;

@ControllerAdvice
public class ProjectExceptionHandler extends ResponseEntityExceptionHandler {
	
	

	
	
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception exception,WebRequest request){
		
		List<String> errorMessages = new ArrayList<>();
		errorMessages.add("An error has occured");
		
		List<String> messages = new ArrayList<>();
		messages.add("Something went wrong");
		
		
		String errorMessageDescription = exception.getLocalizedMessage();
		if(errorMessageDescription == null) errorMessageDescription=exception.toString();
		
		
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setErrorMessages(errorMessages);
		apiErrorResponse.setMessages(messages);
		apiErrorResponse.setStatusCode(500);
		apiErrorResponse.setStatusMessage(errorMessageDescription);
		
		return new ResponseEntity<>(apiErrorResponse,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@ExceptionHandler(value = {ProjectInvalidRequestHeaderException.class})
	public ResponseEntity<Object> handleInvalidRequestException(ProjectInvalidRequestHeaderException exception,WebRequest request){
		
		List<String> errorMessages = new ArrayList<>();
		errorMessages.add("An error has occured");
		
		List<String> messages = new ArrayList<>();
		messages.add("Something went wrong");
		
		
		String errorMessageDescription = exception.getLocalizedMessage();
		if(errorMessageDescription == null) errorMessageDescription=exception.toString();
		
		
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setErrorMessages(errorMessages);
		apiErrorResponse.setMessages(messages);
		apiErrorResponse.setStatusCode(400);
		apiErrorResponse.setStatusMessage(errorMessageDescription);
		
		return new ResponseEntity<>(apiErrorResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {ProjectManagementResponseException.class})
	public ResponseEntity<Object> handleResponseException(ProjectManagementResponseException exception,WebRequest request){
		
		List<String> errorMessages = new ArrayList<>();
		errorMessages.add("An error has occured");
		
		List<String> messages = new ArrayList<>();
		messages.add("Something went wrong");
		
		
		String errorMessageDescription = exception.getLocalizedMessage();
		if(errorMessageDescription == null) errorMessageDescription=exception.toString();
		
		
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setErrorMessages(errorMessages);
		apiErrorResponse.setMessages(messages);
		apiErrorResponse.setStatusCode(500);
		apiErrorResponse.setStatusMessage(errorMessageDescription);
		
		return new ResponseEntity<>(apiErrorResponse,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = {ProjectNotFoundException.class})
	public ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException exception,WebRequest request){
		
		List<String> errorMessages = new ArrayList<>();
		errorMessages.add("An error has occured");
		
		List<String> messages = new ArrayList<>();
		messages.add("Something went wrong");
		
		
		String errorMessageDescription = exception.getLocalizedMessage();
		if(errorMessageDescription == null) errorMessageDescription=exception.toString();
		
		
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setErrorMessages(errorMessages);
		apiErrorResponse.setMessages(messages);
		apiErrorResponse.setStatusCode(404);
		apiErrorResponse.setStatusMessage(errorMessageDescription);
		
		return new ResponseEntity<>(apiErrorResponse,new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(value = {ProjectRequiredFieldsNotPresentException.class})
	public ResponseEntity<Object> projectRequiredFieldsNotPresentException(ProjectRequiredFieldsNotPresentException exception,WebRequest request){
		
		List<String> errorMessages = new ArrayList<>();
		errorMessages.add("Required fields are Empty");
		
		List<String> messages = new ArrayList<>();
		messages.add("Please add the required Field in request body");
		
		
		String errorMessageDescription = exception.getLocalizedMessage();
		if(errorMessageDescription == null) errorMessageDescription=exception.toString();
		
		
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setErrorMessages(errorMessages);
		apiErrorResponse.setMessages(messages);
		apiErrorResponse.setStatusCode(400);
		apiErrorResponse.setStatusMessage(errorMessageDescription);
		
		return new ResponseEntity<>(apiErrorResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(value = {ProjectbodyNullException.class})
	public ResponseEntity<Object> projectProjectBodyNullException(ProjectbodyNullException exception,WebRequest request){
		
		List<String> errorMessages = new ArrayList<>();
		errorMessages.add("Request body is null");
		
		List<String> messages = new ArrayList<>();
		messages.add("Please check the request body again");
		
		
		String errorMessageDescription = exception.getLocalizedMessage();
		if(errorMessageDescription == null) errorMessageDescription=exception.toString();
		
		
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setErrorMessages(errorMessages);
		apiErrorResponse.setMessages(messages);
		apiErrorResponse.setStatusCode(400);
		apiErrorResponse.setStatusMessage(errorMessageDescription);
		
		return new ResponseEntity<>(apiErrorResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = {ApiException.class})
	public ResponseEntity<Object> handleAPIException(ApiException exception,WebRequest request){
		
		List<String> errorMessages = new ArrayList<>();
		errorMessages.add("Failed to process the request ");
		
		List<String> messages = new ArrayList<>();
		messages.add(HttpStatus.INTERNAL_SERVER_ERROR.toString() +"Has Occured");
		
		
		String errorMessageDescription = exception.getLocalizedMessage();
		if(errorMessageDescription == null) errorMessageDescription=exception.toString();
		
		
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setErrorMessages(errorMessages);
		apiErrorResponse.setMessages(messages);
		apiErrorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		apiErrorResponse.setStatusMessage(errorMessageDescription);
		
		return new ResponseEntity<>(apiErrorResponse,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
}
