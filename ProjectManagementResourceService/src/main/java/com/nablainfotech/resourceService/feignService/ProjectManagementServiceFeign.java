package com.nablainfotech.resourceService.feignService;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nablainfotech.resourceService.model.ApiResponseSuccessProject;
import com.nablainfotech.resourceService.model.DeleteResponseSuccess;
import com.nablainfotech.resourceService.model.ProjectDetailsRequestBody;
import com.nablainfotech.resourceService.model.ProjectTeamDetailsRequestBody;
import com.nablainfotech.resourceService.model.ApiResponseSuccessTeam;

import feign.Headers;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@FeignClient(name = "project-management")
public interface ProjectManagementServiceFeign {

	@Headers("Accept: application/json")
	@RequestMapping(value = "/addProjectDetails", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ApiResponseSuccessProject addProjectDetails(
			@Parameter(in = ParameterIn.DEFAULT, description = "Details of the Project being created.", required = true, schema = @Schema()) @Valid @RequestBody ProjectDetailsRequestBody body,
			@Parameter(in = ParameterIn.HEADER, description = "", schema = @Schema(allowableValues = {
					"NIP" })) @RequestHeader(value = "x-tenant-id", required = false) String xTenantId);

	@RequestMapping(value = "/getProject", produces = { "application/json" }, method = RequestMethod.GET)
	ApiResponseSuccessProject getProjectDetails(
			@Parameter(in = ParameterIn.QUERY, description = "An offset is simply the number of records you wish to skip before selecting records", schema = @Schema()) @Valid @RequestParam(value = "offset", required = false) Integer offset,
			@Parameter(in = ParameterIn.QUERY, description = "No of records to fetch", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit);

	@RequestMapping(value = "/deleteProjectDetails/{projectId}", produces = {
			"application/json" }, method = RequestMethod.DELETE)
	ResponseEntity<DeleteResponseSuccess> deleteProjectDetails(@RequestHeader("Accept") String accept,
			@PathVariable("projectId") String projectId);

	@RequestMapping(value = "/updateProjectDetails/{projectId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ApiResponseSuccessProject updateProjectDetails(
			@Parameter(in = ParameterIn.PATH, description = "Id of the project you want to update detials", required = true, schema = @Schema()) @PathVariable("projectId") String projectId,
			@Parameter(in = ParameterIn.DEFAULT, description = "Updated details of the project", required = true, schema = @Schema()) @Valid @RequestBody ProjectDetailsRequestBody body);

	@Headers("Accept: application/json")
	@RequestMapping(value = "/getTeamDetails", produces = { "application/json" }, method = RequestMethod.GET)
	ApiResponseSuccessTeam getTeamDetails(
			@Parameter(in = ParameterIn.QUERY, description = "An offset is simply the number of records you wish to skip before selecting records", schema = @Schema()) @Valid @RequestParam(value = "offset", required = false) int offset,
			@Parameter(in = ParameterIn.QUERY, description = "No of records to fetch", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) int limit);

	@Headers("Accept: application/json")
	@RequestMapping(value = "/createProjectTeam", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ApiResponseSuccessTeam createProjectTeam(
			@Parameter(in = ParameterIn.HEADER, description = "", schema = @Schema(allowableValues = {
					"NIP" })) @RequestHeader(value = "x-tenant-id", required = false) String xTenantId,
			@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody ProjectTeamDetailsRequestBody body);

	@RequestMapping(value = "/getTeamDetails", produces = { "application/json" }, method = RequestMethod.GET)
	ApiResponseSuccessTeam getTeamDetails(
			@Parameter(in = ParameterIn.QUERY, description = "unique id of the Team.", schema = @Schema()) @Valid @RequestParam(value = "teamID", required = false) String teamID,
			@Parameter(in = ParameterIn.QUERY, description = "An offset is simply the number of records you wish to skip before selecting records", schema = @Schema()) @Valid @RequestParam(value = "offset", required = false) int offset,
			@Parameter(in = ParameterIn.QUERY, description = "No of records to fetch", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) int limit);

	
    @RequestMapping(value = "/getProject",
            produces = { "application/json" }, 
            method = RequestMethod.GET)
        ApiResponseSuccessProject getProjectDetails(@Parameter(in = ParameterIn.QUERY, description = "unique id of the project." ,schema=@Schema()) @Valid @RequestParam(value = "projectID", required = false) String projectID,  @Parameter(in = ParameterIn.QUERY, description = "An offset is simply the number of records you wish to skip before selecting records" ,schema=@Schema()) @Valid @RequestParam(value = "offset", required = false) Integer offset, @Parameter(in = ParameterIn.QUERY, description = "No of records to fetch" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit);

}
