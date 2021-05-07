package com.projectmanagement.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmanagement.exceptions.ProjectInvalidRequestHeaderException;
import com.projectmanagement.exceptions.ProjectNotFoundException;
import com.projectmanagement.model.ApiResponseSuccessProject;
import com.projectmanagement.model.ProjectDetailsRequestBody;
import com.projectmanagement.service.ProjectDetailsService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")
@RestController
public class UpdateProjectDetailsApiController implements UpdateProjectDetailsApi {

	private static final Logger log = LoggerFactory.getLogger(UpdateProjectDetailsApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private ProjectDetailsService projectDetailsService;

	@org.springframework.beans.factory.annotation.Autowired
	public UpdateProjectDetailsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	/**
	 * This Controller class is used to update project details
	 *
	 */
	public ResponseEntity<ApiResponseSuccessProject> updateProjectDetails(
			@Parameter(in = ParameterIn.PATH, description = "Id of the project you want to update detials", required = true, schema = @Schema()) @PathVariable("projectId") String projectId,
			@Parameter(in = ParameterIn.DEFAULT, description = "Updated details of the project", required = true, schema = @Schema()) @Valid @RequestBody ProjectDetailsRequestBody requestBody) {
		String accept = request.getHeader("Accept");
		log.warn("Checking the value of Accept in header");
		if (accept != null && accept.contains("application/json")) {
			try {
				log.info("Creating ApiResponseSuccessProject object for response body");
				ApiResponseSuccessProject apiResponseSuccessProject = projectDetailsService
						.getUpdateProjectDetailsResponse(projectId, requestBody);
				
				if (apiResponseSuccessProject.getStatusCode() == HttpStatus.NOT_FOUND.value()) {
					log.error(apiResponseSuccessProject.getStatusMessage());
					return new ResponseEntity<ApiResponseSuccessProject>(apiResponseSuccessProject, HttpStatus.NOT_FOUND);
				} 
				if (apiResponseSuccessProject.getStatusCode() == HttpStatus.OK.value()) {
					log.info("Successfully updated the record");
					return new ResponseEntity<ApiResponseSuccessProject>(apiResponseSuccessProject, HttpStatus.OK);
				}
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<ApiResponseSuccessProject>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		log.error("The provided request Header is invalid", new Exception());
		throw new ProjectInvalidRequestHeaderException("The provided request Header is invalid");
	}

}
