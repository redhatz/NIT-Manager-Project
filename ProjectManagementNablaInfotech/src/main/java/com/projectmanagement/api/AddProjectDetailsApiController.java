package com.projectmanagement.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagement.exceptions.ProjectInvalidRequestHeaderException;
import com.projectmanagement.exceptions.ProjectManagementResponseException;
import com.projectmanagement.exceptions.ProjectRequiredFieldsNotPresentException;
import com.projectmanagement.exceptions.ProjectbodyNullException;
import com.projectmanagement.model.ApiResponseSuccessProject;
import com.projectmanagement.model.ProjectDetailsRequestBody;
import com.projectmanagement.service.ProjectDetailsService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")
@RestController
public class AddProjectDetailsApiController implements AddProjectDetailsApi {

	private static final Logger log = LoggerFactory.getLogger(AddProjectDetailsApiController.class);

	private final HttpServletRequest request;

	@Autowired
	private ProjectDetailsService projectDetailsService;

	@org.springframework.beans.factory.annotation.Autowired
	public AddProjectDetailsApiController(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * Add Project details Controller Class
	 */
	public ResponseEntity<ApiResponseSuccessProject> addProjectDetails(
			@Parameter(in = ParameterIn.DEFAULT, description = "Details of the Project being created.", required = true, schema = @Schema()) @Valid @RequestBody ProjectDetailsRequestBody requestBody,
			@Parameter(in = ParameterIn.HEADER, description = "", schema = @Schema(allowableValues = {
					"NIP" })) @RequestHeader(value = "x-tenant-id", required = false) String xTenantId) {
		String accept = request.getHeader("Accept");
		System.out.println("addProjectDetails called Successfully");
		if (accept != null && accept.contains("application/json")) {
			try {

				System.out.println("addProjectDetails inside accept header");
				System.out.println(requestBody);
				log.info("ApiResponseSuccessProject Object");
				ApiResponseSuccessProject apiResponseSuccessProject = projectDetailsService
						.getAddProjectDetailsResponse(requestBody);


				if (apiResponseSuccessProject.getStatusCode() == HttpStatus.BAD_REQUEST.value()) {
					log.error("Checking if request body properties are not null",
							new Exception().getLocalizedMessage());
					return new ResponseEntity<ApiResponseSuccessProject>(apiResponseSuccessProject,
							HttpStatus.BAD_REQUEST);

				}
				if (apiResponseSuccessProject.getStatusCode() == HttpStatus.CREATED.value()) {
					log.info("201 success response");
					return new ResponseEntity<ApiResponseSuccessProject>(apiResponseSuccessProject, HttpStatus.CREATED);
				}

				log.error("Failed to process the request");
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to process the request ");

			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				throw new ProjectManagementResponseException("Couldn't serialize response for content");
			}
		}
		log.error("The provided request Header is invalid", new Exception());
		throw new ProjectInvalidRequestHeaderException("The provided request Header is invalid");

	}

}
