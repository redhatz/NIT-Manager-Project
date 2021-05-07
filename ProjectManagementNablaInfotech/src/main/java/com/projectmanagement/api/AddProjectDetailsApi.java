/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.projectmanagement.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectmanagement.model.ApiErrorResponse;
import com.projectmanagement.model.ApiResponseSuccessProject;
import com.projectmanagement.model.ProjectDetailsRequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")
@Validated
public interface AddProjectDetailsApi {

    @Operation(summary = "Add new project entry to the database", description = "This service end point takes the new project details and add it to the database. All the details about the project has to be sent in request.", security = {
        @SecurityRequirement(name = "OA2", scopes = {
                    })    }, tags={ "Project" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "New Project Details addition status", content = @Content(schema = @Schema(implementation = ApiResponseSuccessProject.class))),
        
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
        
        @ApiResponse(responseCode = "500", description = "internal server error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))) })
    @RequestMapping(value = "/addProjectDetails",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<ApiResponseSuccessProject> addProjectDetails(@Parameter(in = ParameterIn.DEFAULT, description = "Details of the Project being created.", required=true, schema=@Schema()) @Valid @RequestBody ProjectDetailsRequestBody body, @Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema(allowableValues={ "NIP" }
)) @RequestHeader(value="x-tenant-id", required=false) String xTenantId);

}

