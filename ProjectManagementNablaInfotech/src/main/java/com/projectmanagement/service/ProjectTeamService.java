package com.projectmanagement.service;

import java.lang.reflect.InvocationTargetException;

import javax.validation.Valid;

import com.projectmanagement.entity.ProjectTeamDetails;
import com.projectmanagement.model.ApiResponseSuccessTeam;
import com.projectmanagement.model.DeleteResponseSuccess;
import com.projectmanagement.model.ProjectTeamDetailsRequestBody;

public interface ProjectTeamService {
	
	ApiResponseSuccessTeam getAddProjectTeamResponse(ProjectTeamDetailsRequestBody requestBody) throws IllegalAccessException, NoSuchFieldException;
	
	ProjectTeamDetails createAndUpdateProjectTeam(ProjectTeamDetails teamDetails);

	Boolean deleteProjectTeamDetailsById(String teamId);

	DeleteResponseSuccess getDeleteProjectTeamDetailsResponse(String teamId);

	ApiResponseSuccessTeam getTeamDetailsResponse(@Valid String teamID, @Valid int offset, @Valid int limit) throws IllegalAccessException, InvocationTargetException;

	ApiResponseSuccessTeam getUpdateProjectTeamResponse(String teamID,
			@Valid ProjectTeamDetailsRequestBody requestBody)  throws NoSuchFieldException, IllegalAccessException, InvocationTargetException;

}
