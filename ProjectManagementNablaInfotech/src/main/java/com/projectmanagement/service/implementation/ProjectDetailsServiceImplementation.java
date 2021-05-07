package com.projectmanagement.service.implementation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.projectmanagement.entity.OffsetBasedPageRequest;
import com.projectmanagement.entity.ProjectDetails;
import com.projectmanagement.model.ApiResponseSuccessProject;
import com.projectmanagement.model.DeleteResponseSuccess;
import com.projectmanagement.model.NullAwareBeanUtilsBean;
import com.projectmanagement.model.Pagination;
import com.projectmanagement.model.ProjectDetailsRequestBody;
import com.projectmanagement.model.ProjectDetailsRequestBodyProjectDetails;
import com.projectmanagement.model.ProjectDetailsResponseBody;
import com.projectmanagement.model.ResponseSuccessProject;
import com.projectmanagement.repository.ProjectManagementRepository;
import com.projectmanagement.service.ProjectDetailsService;

import ch.qos.logback.classic.Logger;

@Service
public class ProjectDetailsServiceImplementation implements ProjectDetailsService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ProjectDetailsServiceImplementation.class);
	private static final String LOGGER_SETTER_MESSAGE = "setting field information using setter";

	@Autowired
	private ProjectManagementRepository projectManagementRepository;

	/**
	 * This Function Takes an object and checks each property if its null or not.
	 * 
	 * @param obj
	 * @return boolean value
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 */
	public static Boolean checkNullValueExistInObject(Object obj) throws IllegalAccessException {
		Boolean nullValueExistFlag = false;
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object value = field.get(obj);

			if (!fieldName.equals("projectId") && !fieldName.equals("startDate") && !fieldName.equals("endDate")
					&& !fieldName.equals("modifiedDate") && value == null) {
				log.error("Value of the field is null", new Exception());
				nullValueExistFlag = true;

			}

		}
		return nullValueExistFlag;
	}

	/**
	 * This methods returns true is more than one parameter is passed else it
	 * returns false
	 * 
	 * @param projectID
	 * @param projectName
	 * @param status
	 * @return
	 */
	public static Boolean checkIfOnlyOneParameterPassed(@Valid String projectID, @Valid String projectName,
			@Valid String status) {

		return (projectID != null && projectName != null && status == null)
				|| (projectID == null && projectName != null && status != null)
				|| (projectID != null && projectName == null && status != null)
				|| (projectID != null && projectName != null && status != null);

	}

	/**
	 * This methods checks if project id is linked to any project .If it is linked
	 * then deletes that project.
	 * 
	 * @param project id
	 * @return Boolean value
	 */
	@Override
	public Boolean deleteProjectDetailsById(String projectId) {
		Boolean deleteCheckFlag = false;
		if (projectManagementRepository.findByProjectId(projectId) == null) {
			log.error("Project id is not linked to any project", new Exception());
			return deleteCheckFlag;
		} else {
			log.info("Project linked to the project id deleted successfully");
			projectManagementRepository.deleteByProjectId(projectId);
			deleteCheckFlag = true;
			return deleteCheckFlag;
		}
	}

	/**
	 * This function deletes the project details entry from the database and returns
	 * DeleteResponseSuccess object
	 * 
	 * @param project id
	 * @return DeleteResponseSuccess object
	 *
	 */
	@Override
	public DeleteResponseSuccess getDeleteProjectDetailsResponse(String projectId) {
		DeleteResponseSuccess deleteResponseSuccess = new DeleteResponseSuccess();
		log.info("Checking if Project id is linked to a project. Flag to Check if the Linked entry is deleted ");
		Boolean deleteProjectCheckFlag = deleteProjectDetailsById(projectId);
		log.warn("Checking if any error occured While Deleting the entry");
		if (deleteProjectCheckFlag == Boolean.FALSE) {
			log.error("Error has occured while deleting the project entry/record", new Exception());
			deleteResponseSuccess.setStatusCode(HttpStatus.NOT_FOUND.value());
			deleteResponseSuccess.setStatusMessage("No Project is linked with the project id");
			return deleteResponseSuccess;
		} else {
			log.info("Successfully Deleted the record/entry");
			deleteResponseSuccess.setStatusCode(HttpStatus.OK.value());
			deleteResponseSuccess
					.setStatusMessage("Project linked with the project id:" + projectId + " deleted successfully");
			return deleteResponseSuccess;
		}
	}

	/**
	 * This method fetches the project details by project id
	 * 
	 * @param projectID
	 * @return
	 */
	public ApiResponseSuccessProject getProjectDetailsByProjectId(@Valid String projectID) {

		log.info("Fetching project details by project id");
		Gson gson = new Gson(); // GSON object to convert object to JSON and vice versa
		int successStatusCode = HttpStatus.OK.value(); // success STatus code
		String successMessage = "Project details successfully fetched"; // Success message
		ProjectDetails projectDetailsById = projectManagementRepository.findByProjectId(projectID);
		if (projectDetailsById == null) {
			log.error("No project is linked to the provided project id", new Exception().getLocalizedMessage());
			return new ApiResponseSuccessProject(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
					Arrays.asList("No project is linked to the provided project id"));
		} else {

			log.info("converting ProjectDetails object to ProjectDetailsResponseBody object");
			String projectDetailsByIdJson = gson.toJson(projectDetailsById);
			ProjectDetailsResponseBody projectDetailsResponseBody = gson.fromJson(projectDetailsByIdJson,
					ProjectDetailsResponseBody.class);
			log.info("creating ResponseSuccessProject object");
			ResponseSuccessProject responseSuccessProject = new ResponseSuccessProject(projectDetailsResponseBody);
			log.info(LOGGER_SETTER_MESSAGE);

			return new ApiResponseSuccessProject(successStatusCode, successMessage, new Pagination(0, 1, 1),
					Arrays.asList(responseSuccessProject));

		}
	}

	/**
	 * This method fetches the project details by project name
	 * 
	 * @param projectName
	 * @return
	 */
	public ApiResponseSuccessProject getProjectByProjectName(@Valid String projectName) {
		Gson gson = new Gson(); // GSON object to convert object to JSON and vice versa
		int successStatusCode = HttpStatus.OK.value(); // success STatus code
		String successMessage = "Project details successfully fetched"; // Success message
		projectName = projectName.toUpperCase();
		log.info("Fetching project detail by project name");
		ProjectDetails projectDetailsByName = projectManagementRepository.findByProjectName(projectName);
		if (projectDetailsByName == null) {
			log.error("No project is linked to the provided project Name", new Exception());

			return new ApiResponseSuccessProject(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
					Arrays.asList("No project is linked to the provided project Name"));
		} else {
			log.info("converting ProjectDetails object to ProjectDetailsResponseBody object ");
			String projectDetailsByNameJson = gson.toJson(projectDetailsByName);
			ProjectDetailsResponseBody projectDetailsResponseBody = gson.fromJson(projectDetailsByNameJson,
					ProjectDetailsResponseBody.class);
			log.info("creating ResponseSuccessProject object");
			ResponseSuccessProject responseSuccessProject = new ResponseSuccessProject(projectDetailsResponseBody);
			log.info(LOGGER_SETTER_MESSAGE);
			return new ApiResponseSuccessProject(successStatusCode, successMessage, new Pagination(0, 1, 1),
					Arrays.asList(responseSuccessProject));
		}

	}

	/**
	 * This method fetches the project details by status
	 * @param status
	 * @param offset
	 * @param limit
	 * @return
	 */
	public ApiResponseSuccessProject getProjectByStatus(@Valid String status, @Valid int offset, @Valid int limit) {

		Gson gson = new Gson(); // GSON object to convert object to JSON and vice versa
		int successStatusCode = HttpStatus.OK.value(); // success STatus code
		String successMessage = "Project details successfully fetched"; // Success message
		status = status.toUpperCase();
		log.info("Creating pageable object for pagination");
		Pageable pageable = new OffsetBasedPageRequest(offset, limit);
		log.info("Fetching project detail by status");
		Page<ProjectDetails> projectDetailsByStatusPage = projectManagementRepository.findByStatus(status, pageable);
		List<ProjectDetails> projectDetailsByStatusList;
		projectDetailsByStatusList = projectDetailsByStatusPage.getContent();
		if (projectDetailsByStatusList.isEmpty()) {
			log.error("No project details entry is present in the database with the status: ", new Exception());

			return new ApiResponseSuccessProject(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
					Arrays.asList("No project details entry is present in the database with the status: " + status));
		} else {
			log.info(
					"converting ProjectDetails object to ProjectDetailsResponseBody object and adding it to the list ");
			List<ProjectDetailsResponseBody> projectDetailsResponseBodyList = new ArrayList<>();
			for (ProjectDetails projectDetailsByStatus : projectDetailsByStatusList) {
				String projectDetailsByStatusJson = gson.toJson(projectDetailsByStatus);
				ProjectDetailsResponseBody projectDetailsResponseBody = gson.fromJson(projectDetailsByStatusJson,
						ProjectDetailsResponseBody.class);
				projectDetailsResponseBodyList.add(projectDetailsResponseBody);
			}
			List<ResponseSuccessProject> resultList = new ArrayList<>();
			log.info("traversing another list of ProjectDetailsResponseBody");
			for (ProjectDetailsResponseBody projectDetailsResponse : projectDetailsResponseBodyList) {

				ResponseSuccessProject responseSuccessProject = new ResponseSuccessProject(projectDetailsResponse);
				resultList.add(responseSuccessProject);

			}
			log.info(LOGGER_SETTER_MESSAGE);

			return new ApiResponseSuccessProject(successStatusCode, successMessage,
					new Pagination(offset, limit, projectDetailsByStatusList.size()), resultList);
		}
	}

	/**
	 * This method fetches all the project details
	 * @param offset
	 * @param limit
	 * @return
	 */
	public ApiResponseSuccessProject getAllProjectDetails(@Valid int offset, @Valid int limit) {

		Gson gson = new Gson(); // GSON object to convert object to JSON and vice versa
		int successStatusCode = HttpStatus.OK.value(); // success STatus code
		String successMessage = "Project details successfully fetched"; // Success message
		log.info("Creating pageable object for pagination");
		Pageable pageable = new OffsetBasedPageRequest(offset, limit);
		log.info("fetching all project details");
		Page<ProjectDetails> projectDetailsPage = projectManagementRepository.findAll(pageable);

		List<ProjectDetails> projectDetailsList;
		projectDetailsList = projectDetailsPage.getContent();
		if (projectDetailsList.isEmpty()) {
			log.error("No project details entry present in the database", new Exception());

			return new ApiResponseSuccessProject(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
					Arrays.asList("No project details entry present in the database"));
		} else {
			List<ProjectDetailsResponseBody> projectDetailsResponseBodyList = new ArrayList<>();
			log.info("traversing through a list of projectDetailsList");
			for (ProjectDetails projectDetails : projectDetailsList) {

				String projectDetailsJson = gson.toJson(projectDetails);
				ProjectDetailsResponseBody projectDetailsResponseBody = gson.fromJson(projectDetailsJson,
						ProjectDetailsResponseBody.class);
				projectDetailsResponseBodyList.add(projectDetailsResponseBody);

			}
			List<ResponseSuccessProject> resultList = new ArrayList<>();
			log.info("traversing another list of ProjectDetailsResponseBody");
			for (ProjectDetailsResponseBody projectDetailsResponse : projectDetailsResponseBodyList) {

				ResponseSuccessProject responseSuccessProject = new ResponseSuccessProject(projectDetailsResponse);
				resultList.add(responseSuccessProject);

			}

			log.info(LOGGER_SETTER_MESSAGE);

			return new ApiResponseSuccessProject(successStatusCode, successMessage,
					new Pagination(offset, limit, projectManagementRepository.findAll().size()), resultList);
		}
	}

	/**
	 * 
	 * This function fetches project details. If project id ,project name or status
	 * is provided in the request then project related to those will be fetched
	 * 
	 * @return ApiResponseSuccessProject object
	 * @param projectID
	 * @param projectName
	 * @param status
	 * @param offset
	 * @param limit
	 *
	 */
	@Override
	public ApiResponseSuccessProject getProjectResponse(@Valid String projectID, @Valid String projectName,
			@Valid String status, @Valid int offset, @Valid int limit) {

		log.info("getProjectDetailsResponse invoked");

		log.warn("Checking if more than one parameter is passed for fetching the details");
		if (checkIfOnlyOneParameterPassed(projectID, projectName, status) == Boolean.FALSE) {

			if (projectID != null) {
				return getProjectDetailsByProjectId(projectID);

			} else if (projectName != null) {
				return getProjectByProjectName(projectName);

			} else if (status != null) {
				return getProjectByStatus(status, offset, limit);
			} else {
				return getAllProjectDetails(offset, limit);

			}
		}
		log.error("More than one parameter is passed for fetching the details", new Exception().getLocalizedMessage());

		return new ApiResponseSuccessProject(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
				Arrays.asList("More than one parameter is passed for fetching the details"));

	}

	/**
	 * This function does all the logic related work regarding adding a new project
	 * details entry in the database and then returns a object of
	 * ApiResponseSuccessProject which will is used for response
	 * 
	 * @param requestBody
	 * @return ApiResponseSuccessProject object
	 */
	@Override
	public ApiResponseSuccessProject getAddProjectDetailsResponse(@Valid ProjectDetailsRequestBody requestBody)
			throws Exception {

		log.info("Creating objects of different classes required for ApiResponseSuccessProject object creation");
		Gson gson = new Gson(); // GSON object to convert object to JSON and vice versa
		ResponseSuccessProject responseSuccessProject = new ResponseSuccessProject();
		ApiResponseSuccessProject apiResponseSuccessProject = new ApiResponseSuccessProject();
		ProjectDetailsRequestBodyProjectDetails projectDetailsRequestBody = requestBody.getProjectDetails();
		log.info("Setting modified date");
		DateTimeFormatter formatterInJava8 = DateTimeFormatter.ofPattern("dd MMMM yyyy");

		LocalDateTime localDateTimeCurrentTime = LocalDateTime.now();
		String currentDateAndTime = formatterInJava8.format(localDateTimeCurrentTime);

		projectDetailsRequestBody.setModifiedDate(currentDateAndTime);
		log.info("Fetching project details by project id");
		ProjectDetails projectDetailsByProjectId = projectManagementRepository
				.findByProjectId(projectDetailsRequestBody.getProjectId());
		log.info("Fetching project details by project name");
		ProjectDetails projectDetailsByProjectName = projectManagementRepository
				.findByProjectName(projectDetailsRequestBody.getProjectName().toUpperCase());

		if (projectDetailsByProjectId == null && projectDetailsByProjectName == null) {

			if (checkNullValueExistInObject(projectDetailsRequestBody) == Boolean.TRUE) {

				log.error("A property in the request body is null or empty.Please check the request body",
						new Exception());
				apiResponseSuccessProject.setStatusCode(HttpStatus.BAD_REQUEST.value());
				apiResponseSuccessProject.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
				apiResponseSuccessProject.setErrorMessages(
						Arrays.asList("A property in the request body is null or empty.Please check the request body"));
				return apiResponseSuccessProject;
			} else {
				log.info("converting projectDetailsRequestBody to json");
				String projectDetailsRequestBodyJson = gson.toJson(projectDetailsRequestBody);
				log.info("converting ProjectDetailsRequestBodyProjectDetails to ProjectDetails using json mapping");
				ProjectDetails projectDetailsBody = gson.fromJson(projectDetailsRequestBodyJson, ProjectDetails.class);
				log.info("Setting project name to upper case for easier fetching");
				projectDetailsBody.setProjectName(projectDetailsRequestBody.getProjectName().toUpperCase());
				log.info("saving the request body to the database");
				ProjectDetails savedProjectDetailObject = projectManagementRepository.save(projectDetailsBody);
				String savedProjectDetailObjectJson = gson.toJson(savedProjectDetailObject);
				ProjectDetailsResponseBody projectDetailsResponseBody = gson.fromJson(savedProjectDetailObjectJson,
						ProjectDetailsResponseBody.class);
				responseSuccessProject.setProjectDetails(projectDetailsResponseBody);
				log.info("Setting all the information in apiResponseSuccessProject object");
				apiResponseSuccessProject.setStatusCode(HttpStatus.CREATED.value());
				apiResponseSuccessProject.setStatusMessage("Project Details Added successfully to the database");
				apiResponseSuccessProject.setResults(Arrays.asList(responseSuccessProject));
				apiResponseSuccessProject.setPagination(new Pagination(0, 1, 1));

				return apiResponseSuccessProject;
			}
		} else {
			log.error("In database, A project detail already exist with the same project id or name", new Exception());
			apiResponseSuccessProject.setStatusCode(HttpStatus.BAD_REQUEST.value());
			apiResponseSuccessProject.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
			apiResponseSuccessProject.setErrorMessages(
					Arrays.asList("In database, A project detail already exist with the same project id or name"));
			return apiResponseSuccessProject;
		}

	}

	/**
	 * This function does all the logic related work for updating a project details
	 * and then returns a object of ApiResponseSuccessProject which will is used for
	 * response
	 * 
	 * @param projectId
	 * @param requestBody
	 * @return ApiResponseSuccessProject object
	 *
	 */
	@Override
	public ApiResponseSuccessProject getUpdateProjectDetailsResponse(String projectId,
			@Valid ProjectDetailsRequestBody requestBody) throws IllegalAccessException, InvocationTargetException {

		log.info("creating a object of ProjectDetailsRequestBodyProjectDetails to get project details from request");
		ProjectDetailsRequestBodyProjectDetails projectDetailsRequestBody = requestBody.getProjectDetails();
		log.info("setting project id");
		projectDetailsRequestBody.setProjectId(projectId);
		log.info("Setting modified date");
		DateTimeFormatter formatterInJava8 = DateTimeFormatter.ofPattern("dd MMMM yyyy");

		LocalDateTime localDateTimeCurrentTime = LocalDateTime.now();
		String currentDateAndTime = formatterInJava8.format(localDateTimeCurrentTime);
		projectDetailsRequestBody.setModifiedDate(currentDateAndTime);
		log.info("If project name is there for updation then converting it to upper case");
		if (!projectDetailsRequestBody.getProjectName().isEmpty()) {
			projectDetailsRequestBody.setProjectName(projectDetailsRequestBody.getProjectName().toUpperCase());
		}
		log.info("Fetching the project details by project id");
		ProjectDetails projectDetailsObject = projectManagementRepository.findByProjectId(projectId);
		log.info("creating a object of ApiResponseSuccessProject to return");
		ApiResponseSuccessProject apiResponseSuccessProject = new ApiResponseSuccessProject();
		log.info("creating gson object for converting an object to json and vice versa");
		Gson gson = new Gson();
		String projectDetailsObjectJson = gson.toJson(projectDetailsObject);
		ProjectDetailsRequestBodyProjectDetails projectDetailById = gson.fromJson(projectDetailsObjectJson,
				ProjectDetailsRequestBodyProjectDetails.class);

		log.warn("Checking if projectDetailsObject is not null");
		if (projectDetailsObject == null) {
			log.error("Given projectId is not linked to any project", new Exception());
			apiResponseSuccessProject.setStatusCode(HttpStatus.NOT_FOUND.value());
			apiResponseSuccessProject.setStatusMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
			apiResponseSuccessProject.setErrorMessages(Arrays.asList("Given projectId is not linked to any project"));
			return apiResponseSuccessProject;

		} else {

			log.info(
					"Creating a object of BeanUtilsBean using NullAwareBeanUtilsBean class to copy non null properties between object ");
			BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
			notNull.copyProperties(projectDetailById, projectDetailsRequestBody);
			String projectDetailByIdJson = gson.toJson(projectDetailById);
			ProjectDetails updatedProjectDetailsObject = gson.fromJson(projectDetailByIdJson, ProjectDetails.class);
			log.info("saving the updated project details in the database");
			ProjectDetails updatedProjectDetails = projectManagementRepository.save(updatedProjectDetailsObject);
			log.info("converting ProjectDetails object to ProjectDetailsResponseBody object ");
			String projectDetailsResponseBodyJson = gson.toJson(updatedProjectDetails);
			ProjectDetailsResponseBody projectDetailsResponseBody = gson.fromJson(projectDetailsResponseBodyJson,
					ProjectDetailsResponseBody.class);
			log.info("creating an object of responseSuccessProject");
			ResponseSuccessProject responseSuccessProject = new ResponseSuccessProject();
			responseSuccessProject.setProjectDetails(projectDetailsResponseBody);
			apiResponseSuccessProject.setStatusCode(HttpStatus.OK.value());
			apiResponseSuccessProject.setStatusMessage("Project Details Updated successfully");
			apiResponseSuccessProject.setResults(Arrays.asList(responseSuccessProject));
			apiResponseSuccessProject.setPagination(new Pagination(0, 1, 1));

			return apiResponseSuccessProject;

		}

	}

}
