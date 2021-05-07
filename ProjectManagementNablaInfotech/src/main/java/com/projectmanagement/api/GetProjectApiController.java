package com.projectmanagement.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmanagement.model.ApiResponseSuccessProject;
import com.projectmanagement.service.ProjectDetailsService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")
@RestController
public class GetProjectApiController implements GetProjectApi {

	private static final Logger log = LoggerFactory.getLogger(GetProjectApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private ProjectDetailsService projectDetailsService;

	@org.springframework.beans.factory.annotation.Autowired
	public GetProjectApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	/**
	 * getProjectDetails is a controller to fetch project details
	 *
	 */
	public ResponseEntity<ApiResponseSuccessProject> getProjectDetails(
			@Parameter(in = ParameterIn.QUERY, description = "unique id of the project.", schema = @Schema()) @Valid @RequestParam(value = "projectID", required = false) String projectID,
			@Parameter(in = ParameterIn.QUERY, description = "Name of the project", schema = @Schema()) @Valid @RequestParam(value = "projectName", required = false) String projectName,
			@Parameter(in = ParameterIn.QUERY, description = "current status of the project", schema = @Schema()) @Valid @RequestParam(value = "status", required = false) String status,
			@Parameter(in = ParameterIn.QUERY, description = "An offset is simply the number of records you wish to skip before selecting records", schema = @Schema()) @Valid @RequestParam(value = "offset", required = false) Integer offset,
			@Parameter(in = ParameterIn.QUERY, description = "No of records to fetch", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit) {

		try {
			log.info("Fetching Project Details");

			ApiResponseSuccessProject apiResponseSuccessProject = projectDetailsService
					.getProjectResponse(projectID, projectName, status, offset, limit);

			if (apiResponseSuccessProject.getStatusCode() == HttpStatus.NOT_FOUND.value()) {
				log.error(apiResponseSuccessProject.getStatusMessage());
				return new ResponseEntity<ApiResponseSuccessProject>(apiResponseSuccessProject, HttpStatus.NOT_FOUND);
			}
			if (apiResponseSuccessProject.getStatusCode() == HttpStatus.BAD_REQUEST.value()) {
				log.error(apiResponseSuccessProject.getStatusMessage());
				return new ResponseEntity<ApiResponseSuccessProject>(apiResponseSuccessProject, HttpStatus.BAD_REQUEST);
			} else {
				log.info("Successfully fetched the records");
				return new ResponseEntity<ApiResponseSuccessProject>(apiResponseSuccessProject, HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<ApiResponseSuccessProject>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
