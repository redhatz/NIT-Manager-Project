package com.projectmanagement.service.implementation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.projectmanagement.entity.OffsetBasedPageRequest;
import com.projectmanagement.entity.ProjectTeamDetails;
import com.projectmanagement.model.ApiResponseSuccessTeam;
import com.projectmanagement.model.DeleteResponseSuccess;
import com.projectmanagement.model.NullAwareBeanUtilsBean;
import com.projectmanagement.model.Pagination;
import com.projectmanagement.model.ProjectTeamDetailsRequestBody;
import com.projectmanagement.model.ProjectTeamDetailsRequestBodyTeamMembersDetails;
import com.projectmanagement.model.ProjectTeamDetailsResponseBody;
import com.projectmanagement.model.ResponseSuccessTeam;
import com.projectmanagement.repository.ProjectTeamRepository;
import com.projectmanagement.service.ProjectTeamService;

import ch.qos.logback.classic.Logger;

@Service
public class ProjectTeamServiceImplementation implements ProjectTeamService {

	@Autowired
	ProjectTeamRepository projectTeamRepository;

	private static final Logger log = (Logger) LoggerFactory.getLogger(ProjectDetailsServiceImplementation.class);

	private static final String NO_ERROR_OCCURED_MESSAGE = "No error occured";

	private static final String OPERATION_SUCCESSFULL_MESSAGE = "Operation done successfully";

	/**
	 * This Function Takes an object and checks each property if its null or not.
	 * 
	 * @param obj
	 * @return boolean value
	 * @throws IllegalAccessException
	 * 
	 */
	public static Boolean checkNullValueExistInObject(Object obj) throws IllegalAccessException {
		Boolean nullValueExistFlag = false;
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object value = field.get(obj);

			if (!fieldName.equals("teamId") && value == null) {

				log.error("Value of the field is null", new Exception());
				nullValueExistFlag = true;

			}

		}
		return nullValueExistFlag;
	}

	/**
	 * This methods Updates and saves project team details to the database
	 * 
	 * @param ProjectTeamDetails object
	 * @return ProjectTeamDetails object
	 */
	@Override
	public ProjectTeamDetails createAndUpdateProjectTeam(ProjectTeamDetails teamDetails) {
		return projectTeamRepository.save(teamDetails);
	}

	/**
	 * This methods saves the request body i.e the team details to the database and
	 * returns ApiResponseSuccessTeam object Which be be used for response body
	 * 
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @param requestBody
	 *
	 */
	@Override
	public ApiResponseSuccessTeam getAddProjectTeamResponse(ProjectTeamDetailsRequestBody requestBody)
			throws IllegalAccessException, NoSuchFieldException {
		ApiResponseSuccessTeam apiResponseSuccessTeam = new ApiResponseSuccessTeam();
		ProjectTeamDetails projectTeamDetailsByProjectAssigned = projectTeamRepository
				.findByProjectAssigned(requestBody.getProjectAssigned());
		if (projectTeamDetailsByProjectAssigned == null) {
			log.warn("checking request body");
			if (checkNullValueExistInObject(requestBody) == Boolean.TRUE) {
				log.error("required field in request body is null", new Exception());
				apiResponseSuccessTeam.setStatusCode(HttpStatus.BAD_REQUEST.value());
				apiResponseSuccessTeam.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
				apiResponseSuccessTeam.setErrorMessages(Arrays.asList("required field in request body is null"));
				return apiResponseSuccessTeam;
			} else {
				log.info("Creating gson object");
				Gson gson = new Gson();
				log.info("converting ProjectTeamDetailsRequestBody object to ProjectTeamDetails object");
				String requestBodyJson = gson.toJson(requestBody);
				ProjectTeamDetails teamDetails = gson.fromJson(requestBodyJson, ProjectTeamDetails.class);

				log.info("saving the request i.e the team details to the database");
				ProjectTeamDetails projectTeamDetails = createAndUpdateProjectTeam(teamDetails);

				log.info("converting ProjectTeamDetails to ProjectTeamDetailsResponseBody ");
				String projectTeamDetailsJson = gson.toJson(projectTeamDetails);
				ProjectTeamDetailsResponseBody projectTeamDetailsResponseBody = gson.fromJson(projectTeamDetailsJson,
						ProjectTeamDetailsResponseBody.class);

				log.info("creating ResponseSuccessTeam object for setting projectTeamDetailsResponseBody");
				ResponseSuccessTeam responseSuccessTeamObject = new ResponseSuccessTeam();
				responseSuccessTeamObject.setTeamDetails(projectTeamDetailsResponseBody);

				log.info("creating ApiResponseSuccessTeam object");

				apiResponseSuccessTeam.setResults(Arrays.asList(responseSuccessTeamObject));
				apiResponseSuccessTeam.setStatusCode(HttpStatus.CREATED.value());
				apiResponseSuccessTeam.setPagination(new Pagination(0, 1, 1));
				apiResponseSuccessTeam.setErrorMessages(Arrays.asList(NO_ERROR_OCCURED_MESSAGE));
				apiResponseSuccessTeam.setMessages(Arrays.asList("Project Team Entry Created Successfully"));
				apiResponseSuccessTeam.setStatusMessage(OPERATION_SUCCESSFULL_MESSAGE);

				return apiResponseSuccessTeam;
			}
		} else {
			log.error("Value of Project assigned is already present in another team details", new Exception());
			apiResponseSuccessTeam.setStatusCode(HttpStatus.BAD_REQUEST.value());
			apiResponseSuccessTeam.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
			apiResponseSuccessTeam.setErrorMessages(
					Arrays.asList("Value of Project assigned is already present in another team details"));
			return apiResponseSuccessTeam;
		}
	}

	/**
	 * this methods first checks if the team id is related to any team details if
	 * yes then it deletes that entry and return true else it returns false
	 *
	 * @param teamId
	 * @return Boolean
	 */
	@Override
	public Boolean deleteProjectTeamDetailsById(String teamId) {
		Boolean teamDeleteCheckFlag = false;
		if (projectTeamRepository.findByTeamId(teamId) == null) {
			return teamDeleteCheckFlag;
		} else {
			projectTeamRepository.deleteByTeamId(teamId);
			teamDeleteCheckFlag = true;
			return teamDeleteCheckFlag;
		}

	}

	/**
	 * This function takes team id as parameter and if any team is linked to that
	 * team id then it deletes the record
	 *
	 * @param teamId
	 * @return DeleteResponseSuccess
	 */
	@Override
	public DeleteResponseSuccess getDeleteProjectTeamDetailsResponse(String teamId) {
		log.info("Creating DeleteResponseSuccess object");
		DeleteResponseSuccess deleteResponseSuccess = new DeleteResponseSuccess();
		log.info("Checking team id and delteting the record");
		Boolean deleteProjectTeamCheckFlag = deleteProjectTeamDetailsById(teamId);
		log.warn("Checking team id");
		if (deleteProjectTeamCheckFlag == Boolean.FALSE) {
			log.error("team id is not linked to any team ", new Exception());
			deleteResponseSuccess.setStatusCode(HttpStatus.NOT_FOUND.value());
			deleteResponseSuccess.setStatusMessage("team id is not linked to any team");
			return deleteResponseSuccess;
		} else {
			log.info("Successfully deleted the record");
			deleteResponseSuccess.setStatusCode(HttpStatus.OK.value());
			deleteResponseSuccess
					.setStatusMessage("Team linked with the provided team id: " + teamId + " deleted successfully");
			return deleteResponseSuccess;
		}

	}

	/**
	 * This method is used to fetch the team details from the database.If team id is
	 * provided the details linked to that id will be fetched.If no team id is not
	 * prided then all the details will be fetched
	 * 
	 * @param teamID
	 * @param offset
	 * @param limit
	 * @return ApiResponseSuccessTeam object
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 *
	 */
	@Override
	public ApiResponseSuccessTeam getTeamDetailsResponse(@Valid String teamID, @Valid int offset, @Valid int limit)
			throws IllegalAccessException, InvocationTargetException {
		log.info("Creating required objects for response");
		ApiResponseSuccessTeam apiResponseSuccessTeam = new ApiResponseSuccessTeam();

		log.warn("checking is teamid is null");
		log.info("Team id is present therefore fetching the details by team id");
		if (teamID != null) {
			ProjectTeamDetails projectTeamDetailsObject = projectTeamRepository.findByTeamId(teamID);
			if (projectTeamDetailsObject == null) {
				log.error("No team detail is linked to the provided team id", new Exception());
				apiResponseSuccessTeam.setStatusCode(HttpStatus.NOT_FOUND.value());
				apiResponseSuccessTeam.setStatusMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
				apiResponseSuccessTeam
						.setErrorMessages(Arrays.asList("No team detail is linked to the provided team id"));
				return apiResponseSuccessTeam;
			} else {

				log.info("converting project team projectTeamDetails Object to projectTeamDetailsResponseBody object");
				ProjectTeamDetailsResponseBody projectTeamDetailsResponseBody = new ProjectTeamDetailsResponseBody();
				BeanUtils.copyProperties(projectTeamDetailsResponseBody, projectTeamDetailsObject);
				ResponseSuccessTeam responseSuccessTeam = new ResponseSuccessTeam();
				responseSuccessTeam.setTeamDetails(projectTeamDetailsResponseBody);
				log.info("setting all the required fields");
				apiResponseSuccessTeam.setResults(Arrays.asList(responseSuccessTeam));
				apiResponseSuccessTeam.setStatusCode(HttpStatus.OK.value());
				apiResponseSuccessTeam.setPagination(new Pagination(0, 1, 1));
				apiResponseSuccessTeam.setErrorMessages(Arrays.asList(NO_ERROR_OCCURED_MESSAGE));
				apiResponseSuccessTeam.setMessages(List.of("Team Details linked to the team id successfully fetched"));
				apiResponseSuccessTeam.setStatusMessage(OPERATION_SUCCESSFULL_MESSAGE);

				return apiResponseSuccessTeam;
			}
		} else {

			log.info("No team id present therefore fetching all the team details");
			log.info("Creasting ResponseSuccessTeam list");
			List<ResponseSuccessTeam> resultsList = new ArrayList<>();
			log.info("Creating pageable object for pagination");
			Pageable pageable = new OffsetBasedPageRequest(offset, limit);
			Page<ProjectTeamDetails> projectTeamDetailsPage = projectTeamRepository.findAll(pageable);
			List<ProjectTeamDetails> projectTeamDetailsList;

			projectTeamDetailsList = projectTeamDetailsPage.getContent();
			if (projectTeamDetailsList.isEmpty()) {
				log.error("No team details Present", new Exception());
				apiResponseSuccessTeam.setStatusCode(HttpStatus.NOT_FOUND.value());
				apiResponseSuccessTeam.setStatusMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
				apiResponseSuccessTeam.setErrorMessages(Arrays.asList("No team details Present"));
				return apiResponseSuccessTeam;
			}

			log.info(
					"coverting object of projectTeamDetails to projectTeamDetailsResponseBody and then adding it to the list");
			for (ProjectTeamDetails projectTeamDetails : projectTeamDetailsList) {
				ProjectTeamDetailsResponseBody projectTeamDetailsResponseBody = new ProjectTeamDetailsResponseBody();
				BeanUtils.copyProperties(projectTeamDetailsResponseBody, projectTeamDetails);
				ResponseSuccessTeam responseSuccessTeam = new ResponseSuccessTeam();
				responseSuccessTeam.setTeamDetails(projectTeamDetailsResponseBody);
				resultsList.add(responseSuccessTeam);

			}

			log.info("setting the required fields");
			apiResponseSuccessTeam.setResults(resultsList);
			apiResponseSuccessTeam.setStatusCode(HttpStatus.OK.value());
			apiResponseSuccessTeam.setPagination(new Pagination(offset, limit, projectTeamRepository.findAll().size()));
			apiResponseSuccessTeam.setErrorMessages(Arrays.asList(NO_ERROR_OCCURED_MESSAGE));
			apiResponseSuccessTeam.setMessages(List.of("Team Details  successfully fetched"));
			apiResponseSuccessTeam.setStatusMessage(OPERATION_SUCCESSFULL_MESSAGE);

			return apiResponseSuccessTeam;
		}

	}

	/**
	 * This method is used to copy properties from one object to another ignoring
	 * the null values
	 * 
	 * @param destination
	 * @param source
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 */
	public static void copyDiff(ProjectTeamDetailsRequestBody destination, ProjectTeamDetailsRequestBody source)
			throws IllegalAccessException, NoSuchFieldException {
		for (Field field : source.getClass().getDeclaredFields()) {
			field.setAccessible(true);

			String name = field.getName();

			Object value = field.get(source);

			if (value != null) {

				Field destField = destination.getClass().getDeclaredField(name);
				destField.setAccessible(true);

				destField.set(destination, value);

			}

		}
	}

	/**
	 * This method is used to copy properties from one object to another ignoring
	 * the null values
	 * 
	 * @param destination
	 * @param source
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 */
	public static void copyDiff(ProjectTeamDetailsRequestBodyTeamMembersDetails destination,
			ProjectTeamDetailsRequestBodyTeamMembersDetails source)
			throws IllegalAccessException, NoSuchFieldException {
		for (Field field : source.getClass().getDeclaredFields()) {
			field.setAccessible(true);

			String name = field.getName();

			Object value = field.get(source);

			if (value != null) {

				Field destField = destination.getClass().getDeclaredField(name);
				destField.setAccessible(true);

				destField.set(destination, value);

			}

		}
	}

	/**
	 * This method takes team id and the request body as parameter .First fetches
	 * the saved data and then updates that data and use that as response
	 * 
	 * @param teamID
	 * @param requestBody
	 * @return ApiResponseSuccessTeam object
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *
	 */
	@Override
	public ApiResponseSuccessTeam getUpdateProjectTeamResponse(String teamID,
			@Valid ProjectTeamDetailsRequestBody requestBody)
			throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
		requestBody.setTeamId(teamID);
		log.info("creating Gson object");
		Gson gson = new Gson();
		log.info("creating objects required for ApiResponseSuccessTeam object");
		ApiResponseSuccessTeam apiResponseSuccessTeam = new ApiResponseSuccessTeam();
		ProjectTeamDetailsResponseBody projectTeamDetailsResponseBody = new ProjectTeamDetailsResponseBody();
		ResponseSuccessTeam responseSuccessTeam = new ResponseSuccessTeam();
		List<ProjectTeamDetailsRequestBodyTeamMembersDetails> teamMembersDetailsList = new ArrayList<>();
		ProjectTeamDetailsRequestBodyTeamMembersDetails projectTeamMembersDetails;
		ProjectTeamDetailsRequestBodyTeamMembersDetails projectTeamDetailsRequestBodyTeamMembersDetails = null;
		log.info("fetching team by id");
		ProjectTeamDetails projectTeamDetailsByTeamId = projectTeamRepository.findByTeamId(teamID);
		log.warn("checking if projectTeamDetails is null");
		if (projectTeamDetailsByTeamId == null) {
			log.error("Provided team id is not linked to any project team");
			apiResponseSuccessTeam.setStatusCode(HttpStatus.NOT_FOUND.value());
			apiResponseSuccessTeam.setStatusMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
			apiResponseSuccessTeam
					.setErrorMessages(Arrays.asList("Provided team id is not linked to any project team"));
			return apiResponseSuccessTeam;
		} else {

			log.info("Converting object of projectTeamDetails to ProjectTeamDetailsRequestBody objects");
			String projectTeamDetailsJson = gson.toJson(projectTeamDetailsByTeamId);
			ProjectTeamDetailsRequestBody projectTeamDetailsByTeamIdRequestBodyObject = gson
					.fromJson(projectTeamDetailsJson, ProjectTeamDetailsRequestBody.class);

			log.info("updating the team members details first");
			int sizeOfProjectTeamDetailsByTeamIdRequestBodyObjectTeamMembersDetailsList = projectTeamDetailsByTeamIdRequestBodyObject
					.getTeamMembersDetails().size();
			int sizeOfProjectTeamDetailsRequestBodyTeamMembersDetailsList = requestBody.getTeamMembersDetails().size();
			for (int counter = 0; counter < sizeOfProjectTeamDetailsByTeamIdRequestBodyObjectTeamMembersDetailsList; counter++) {

				projectTeamMembersDetails = projectTeamDetailsByTeamIdRequestBodyObject.getTeamMembersDetails()
						.get(counter);

				if (counter < sizeOfProjectTeamDetailsRequestBodyTeamMembersDetailsList) {

					projectTeamDetailsRequestBodyTeamMembersDetails = requestBody.getTeamMembersDetails().get(counter);

					BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
					notNull.copyProperties(projectTeamMembersDetails, projectTeamDetailsRequestBodyTeamMembersDetails);
				}

				teamMembersDetailsList.add(projectTeamMembersDetails);
			}
			log.info("updating other properties");
			copyDiff(projectTeamDetailsByTeamIdRequestBodyObject, requestBody);
			log.info("setting team members details list");
			projectTeamDetailsByTeamIdRequestBodyObject.setTeamMembersDetails(teamMembersDetailsList);
			log.info("converting object of projectTeamDetailsRequestBody to ProjectTeamDetails");
			String projectTeamDetailsRequestBodyJson = gson.toJson(projectTeamDetailsByTeamIdRequestBodyObject);
			ProjectTeamDetails projectTeamDetailsUpdated = gson.fromJson(projectTeamDetailsRequestBodyJson,
					ProjectTeamDetails.class);
			log.info("saving the updated details to the database");
			ProjectTeamDetails updatedProjectTeamDetails = projectTeamRepository.save(projectTeamDetailsUpdated);
			log.info("copying properties of ProjectTeamDetails to ProjectTeamDetailsResponseBody");
			BeanUtils.copyProperties(projectTeamDetailsResponseBody, updatedProjectTeamDetails);

			responseSuccessTeam.setTeamDetails(projectTeamDetailsResponseBody);

			apiResponseSuccessTeam.setResults(Arrays.asList(responseSuccessTeam));
			apiResponseSuccessTeam.setStatusCode(HttpStatus.OK.value());
			apiResponseSuccessTeam.setPagination(new Pagination(0, 1, 1));
			apiResponseSuccessTeam.setErrorMessages(Arrays.asList(NO_ERROR_OCCURED_MESSAGE));
			apiResponseSuccessTeam.setMessages(List.of("Team Details updated Successfully"));
			apiResponseSuccessTeam.setStatusMessage(OPERATION_SUCCESSFULL_MESSAGE);

			return apiResponseSuccessTeam;
		}

	}

}
