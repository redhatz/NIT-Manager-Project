package com.projectmanagement.api;

import com.projectmanagement.model.ApiErrorResponse;

import com.projectmanagement.model.DeleteResponseSuccess;
import com.projectmanagement.service.ProjectDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")
@RestController
public class DeleteProjectDetailsApiController implements DeleteProjectDetailsApi {

	private static final Logger log = LoggerFactory.getLogger(DeleteProjectDetailsApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private ProjectDetailsService projectDetailsService;

	@org.springframework.beans.factory.annotation.Autowired
	public DeleteProjectDetailsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	/**
	 * Delete controller to delete project detail entry from the database
	 */
	public ResponseEntity<DeleteResponseSuccess> deleteProjectDetails(
			@Parameter(in = ParameterIn.PATH, description = "Id of the project you want to delete details", required = true, schema = @Schema()) @PathVariable("projectId") String projectId) {
		log.warn("Checking Value of Accept field in Header");
		try {
			log.info("Getting object of DeleteResponseSuccess for response body ");
			DeleteResponseSuccess deleteProjectDetailsResponse = projectDetailsService
					.getDeleteProjectDetailsResponse(projectId);
			if (deleteProjectDetailsResponse.getStatusCode() == HttpStatus.NOT_FOUND.value()) {
				log.error(deleteProjectDetailsResponse.getStatusMessage());
				return new ResponseEntity<DeleteResponseSuccess>(deleteProjectDetailsResponse, HttpStatus.NOT_FOUND);
			} else {
				log.info("Successfully deleted the entry from the database");
				return new ResponseEntity<DeleteResponseSuccess>(deleteProjectDetailsResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<DeleteResponseSuccess>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
