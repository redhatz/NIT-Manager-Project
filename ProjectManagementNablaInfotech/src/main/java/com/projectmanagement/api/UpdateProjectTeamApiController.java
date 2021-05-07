package com.projectmanagement.api;

import com.projectmanagement.exceptions.ProjectInvalidRequestHeaderException;
import com.projectmanagement.exceptions.ProjectNotFoundException;
import com.projectmanagement.model.ApiErrorResponse;
import com.projectmanagement.model.ApiResponseSuccessTeam;
import com.projectmanagement.model.ProjectTeamDetailsRequestBody;
import com.projectmanagement.service.ProjectTeamService;
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
public class UpdateProjectTeamApiController implements UpdateProjectTeamApi {

	private static final Logger log = LoggerFactory.getLogger(UpdateProjectTeamApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private ProjectTeamService projectTeamService;

	@org.springframework.beans.factory.annotation.Autowired
	public UpdateProjectTeamApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ApiResponseSuccessTeam> updateProjectTeam(
			@Parameter(in = ParameterIn.PATH, description = "Unique Id for to identify every team", required = true, schema = @Schema()) @PathVariable("teamID") String teamID,
			@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody ProjectTeamDetailsRequestBody requestBody) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {

				ApiResponseSuccessTeam apiResponseSuccessTeam = projectTeamService.getUpdateProjectTeamResponse(teamID,
						requestBody);
				if (apiResponseSuccessTeam.getStatusCode() == HttpStatus.NOT_FOUND.value()) {
					log.error(apiResponseSuccessTeam.getStatusMessage());
					return new ResponseEntity<ApiResponseSuccessTeam>(apiResponseSuccessTeam, HttpStatus.NOT_FOUND);
				} 
				if(apiResponseSuccessTeam.getStatusCode() == HttpStatus.OK.value()){
					log.info("Successfully updated the records");
					return new ResponseEntity<ApiResponseSuccessTeam>(apiResponseSuccessTeam, HttpStatus.OK);
				}
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<ApiResponseSuccessTeam>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		log.error("The provided request Header is invalid", new Exception());
		throw new ProjectInvalidRequestHeaderException("The provided request Header is invalid");
	}

}
