package com.projectmanagement.service;

import java.lang.reflect.InvocationTargetException;

import javax.validation.Valid;

import com.projectmanagement.model.ApiResponseSuccessProject;
import com.projectmanagement.model.DeleteResponseSuccess;
import com.projectmanagement.model.ProjectDetailsRequestBody;

public interface ProjectDetailsService {

	Boolean deleteProjectDetailsById(String projectId);

	DeleteResponseSuccess getDeleteProjectDetailsResponse(String projectId);

	ApiResponseSuccessProject getProjectResponse(@Valid String projectID, @Valid String projectName,
			@Valid String status, @Valid int offset, @Valid int limit);

	ApiResponseSuccessProject getAddProjectDetailsResponse(@Valid ProjectDetailsRequestBody requestBody) throws Exception;

	ApiResponseSuccessProject getUpdateProjectDetailsResponse(String projectId,
			@Valid ProjectDetailsRequestBody requestBody) throws IllegalAccessException, InvocationTargetException;

}
