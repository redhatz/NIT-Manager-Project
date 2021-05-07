package com.projectmanagement.api;

import com.projectmanagement.model.ApiErrorResponse;
import com.projectmanagement.model.ApiResponseSuccessTeam;
import com.projectmanagement.service.ProjectTeamService;

import java.math.BigDecimal;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")
@RestController
public class GetTeamDetailsApiController implements GetTeamDetailsApi {

	private static final Logger log = LoggerFactory.getLogger(GetTeamDetailsApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;
	@Autowired
	private ProjectTeamService projectTeamService;

	@org.springframework.beans.factory.annotation.Autowired
	public GetTeamDetailsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ApiResponseSuccessTeam> getTeamDetails(
			@Parameter(in = ParameterIn.QUERY, description = "unique id of the Team.", schema = @Schema()) @Valid @RequestParam(value = "teamID", required = false) String teamID,
			@Parameter(in = ParameterIn.QUERY, description = "An offset is simply the number of records you wish to skip before selecting records", schema = @Schema()) @Valid @RequestParam(value = "offset", required = false) int offset,
			@Parameter(in = ParameterIn.QUERY, description = "No of records to fetch", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) int limit) {

		try {

			ApiResponseSuccessTeam apiResponseSuccessTeam = projectTeamService.getTeamDetailsResponse(teamID, offset,
					limit);
			if (apiResponseSuccessTeam.getStatusCode() == HttpStatus.NOT_FOUND.value()) {
				log.error(apiResponseSuccessTeam.getStatusMessage());
				return new ResponseEntity<ApiResponseSuccessTeam>(apiResponseSuccessTeam, HttpStatus.NOT_FOUND);
			} else {
				log.info("Successfully fetched the records");
				return new ResponseEntity<ApiResponseSuccessTeam>(apiResponseSuccessTeam, HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<ApiResponseSuccessTeam>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
