package com.nablainfotech.resourceService.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nablainfotech.resourceService.feignService.ProjectManagementServiceFeign;
import com.nablainfotech.resourceService.model.ApiResponseSuccessProject;
import com.nablainfotech.resourceService.model.ApiResponseSuccessTeam;
import com.nablainfotech.resourceService.model.ProjectDetailsRequestBody;
import com.nablainfotech.resourceService.model.ProjectDetailsRequestBodyProjectDetails;
import com.nablainfotech.resourceService.model.ProjectDetailsRequestBodyProjectDetailsTechnology;
import com.nablainfotech.resourceService.model.ProjectDetailsRequestBodyProjectDetailsTechnology.ProjectTypeEnum;
import com.nablainfotech.resourceService.model.ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase;
import com.nablainfotech.resourceService.model.ProjectDetailsResponseBody;
import com.nablainfotech.resourceService.model.ProjectDetailsResponseBody.StatusEnum;
import com.nablainfotech.resourceService.model.ProjectTeamDetailsRequestBody;
import com.nablainfotech.resourceService.model.ProjectTeamDetailsRequestBodyTeamMembersDetails;
import com.nablainfotech.resourceService.model.ProjectTeamDetailsRequestBodyTeamMembersDetails.PositionEnum;
import com.nablainfotech.resourceService.model.ProjectTeamDetailsResponseBody;
import com.nablainfotech.resourceService.model.ResponseSuccessProject;
import com.nablainfotech.resourceService.model.ResponseSuccessTeam;

@Controller
public class MainController {

	@Autowired
	ProjectManagementServiceFeign projectManagementServiceFeign;

	@GetMapping(value = { "/home", "/" })
	public String home(Model model) {
		ApiResponseSuccessProject projectDetails = projectManagementServiceFeign.getProjectDetails(0, 5);
		ApiResponseSuccessTeam teamDetails = projectManagementServiceFeign.getTeamDetails(0, 5);
		List<ResponseSuccessProject> results = projectDetails.getResults();
		List<ProjectDetailsResponseBody> projectDetailsResponseBodyList = new ArrayList<>();
		List<ProjectTeamDetailsResponseBody> teamDetailsResponseBodyList = new ArrayList<>();

		for (ResponseSuccessProject responseSuccessProject : results) {
			ProjectDetailsResponseBody projectDetails3 = responseSuccessProject.getProjectDetails();
			projectDetailsResponseBodyList.add(projectDetails3);
		}
		List<ResponseSuccessTeam> responseSuccessTeamList = teamDetails.getResults();
		for (ResponseSuccessTeam responseSuccessTeam : responseSuccessTeamList) {
			ProjectTeamDetailsResponseBody teamDetailsResponseBody = responseSuccessTeam.getTeamDetails();
			teamDetailsResponseBodyList.add(teamDetailsResponseBody);
		}

		model.addAttribute("teamDetailsList", teamDetailsResponseBodyList);
		model.addAttribute("projectDetailsList", projectDetailsResponseBodyList);
		model.addAttribute("statusEnum", StatusEnum.values());
		model.addAttribute("projectType", ProjectTypeEnum.values());
		model.addAttribute("position", PositionEnum.values());

		return "index";
	}

	@PostMapping("/addProjectDetailsForm")
	public RedirectView addProjectDetails(@ModelAttribute ProjectDetailsRequestBodyProjectDetails projectDetails,
			@ModelAttribute ProjectDetailsRequestBodyProjectDetailsTechnology projectDetailsRequestBodyTechnology,
			@ModelAttribute ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase projectDetailsRequestBodyTechnologyDatabase,
			HttpServletRequest request) throws Exception {

		int counterDatabaseNamesArray = 0;

		List<ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase> projectDetailsRequestBodyTechnologyDatabaseList = new ArrayList<>();
		
		ProjectTeamDetailsResponseBody projectTeamDetailsResponseBody = new ProjectTeamDetailsResponseBody();
		String[] databaseNamesArray = projectDetailsRequestBodyTechnologyDatabase.getDatabaseName().split(",");
		do {
			ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase projectDetailsRequestBodyTechnologyDatabaseNames = new ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase();
			projectDetailsRequestBodyTechnologyDatabaseNames
					.setDatabaseName(databaseNamesArray[counterDatabaseNamesArray]);
			projectDetailsRequestBodyTechnologyDatabaseList.add(projectDetailsRequestBodyTechnologyDatabaseNames);
			counterDatabaseNamesArray++;
		} while (counterDatabaseNamesArray < databaseNamesArray.length);
		projectDetailsRequestBodyTechnology.setDatabase(projectDetailsRequestBodyTechnologyDatabaseList);
		String projectTeamId = request.getParameter("ProjectTeamId");
		ApiResponseSuccessTeam apiResponseSuccessTeam = projectManagementServiceFeign.getTeamDetails(projectTeamId, 0,
				1);
		List<ResponseSuccessTeam> responseSuccessTeamList = apiResponseSuccessTeam.getResults();

		for (ResponseSuccessTeam responseSuccessTeam : responseSuccessTeamList) {
			projectTeamDetailsResponseBody = responseSuccessTeam.getTeamDetails();
		}
		Gson gson = new Gson(); // GSON object to convert object to JSON and vice versa
		String projectTeamDetailsResponseBodyJson = gson.toJson(projectTeamDetailsResponseBody);
		ProjectTeamDetailsRequestBody projectTeamDetailsRequestBody = gson.fromJson(projectTeamDetailsResponseBodyJson,
				ProjectTeamDetailsRequestBody.class);

		projectDetails
				.setStatus(com.nablainfotech.resourceService.model.ProjectDetailsRequestBodyProjectDetails.StatusEnum
						.valueOf(projectDetails.getStatus().toString().toUpperCase()));
		projectDetails.setTeamDetails(projectTeamDetailsRequestBody);
		projectDetailsRequestBodyTechnology.setProjectType(
				ProjectTypeEnum.valueOf(projectDetailsRequestBodyTechnology.getProjectType().toString().toUpperCase()));
		projectDetails.setTechnology(projectDetailsRequestBodyTechnology);
		ProjectDetailsRequestBody projectDetailsRequestBody = new ProjectDetailsRequestBody();
		projectDetailsRequestBody.setProjectDetails(projectDetails);
		ApiResponseSuccessProject addProjectDetails = projectManagementServiceFeign
				.addProjectDetails(projectDetailsRequestBody, "NIP");
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/home");
		return redirectView;
	}

	@PostMapping("/createTeam")
	public RedirectView createTeam(@ModelAttribute ProjectTeamDetailsRequestBody projectTeamDetailsRequestBody,
			@ModelAttribute ProjectTeamDetailsRequestBodyTeamMembersDetails requestBodyTeamMembersDetails,
			HttpServletRequest request) throws Exception {

		int counterTeamMembersArray = 0;
		String[] valuesArray = null;

		List<ProjectTeamDetailsRequestBodyTeamMembersDetails> projectTeamDetailsRequestBodyTeamMembersDetailsList = new ArrayList<>();

		Class<?> className = requestBodyTeamMembersDetails.getClass();
		do {
			ProjectTeamDetailsRequestBodyTeamMembersDetails projectTeamDetailsTeamMembersDetails = new ProjectTeamDetailsRequestBodyTeamMembersDetails();
			for (Field field : className.getDeclaredFields()) {
				field.setAccessible(true);
				String fieldName = field.getName();
				Object object = field.get(requestBodyTeamMembersDetails);
				if (fieldName != "position") {
					valuesArray = object.toString().split(",");

					setField(projectTeamDetailsTeamMembersDetails, fieldName, valuesArray[counterTeamMembersArray]);
				} else {
					valuesArray = object.toString().split(",");
					String value = valuesArray[counterTeamMembersArray].toUpperCase();
					setField(projectTeamDetailsTeamMembersDetails, fieldName, PositionEnum.valueOf(value));
				}

			}

			projectTeamDetailsRequestBodyTeamMembersDetailsList.add(projectTeamDetailsTeamMembersDetails);
			counterTeamMembersArray++;
		} while (counterTeamMembersArray < valuesArray.length);
		projectTeamDetailsRequestBody.setTeamMembersDetails(projectTeamDetailsRequestBodyTeamMembersDetailsList);
		ApiResponseSuccessTeam createProjectTeamResponse = projectManagementServiceFeign.createProjectTeam("NIP",
				projectTeamDetailsRequestBody);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/home");
		return redirectView;
	}

	@RequestMapping("/delete/{projectId}")
	public RedirectView deleteUser(@PathVariable("projectId") String projectId, HttpServletRequest request) {
		projectManagementServiceFeign.deleteProjectDetails("application/json", projectId);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}

	@PostMapping("/updateProject")
	public RedirectView updateProject(@ModelAttribute ProjectDetailsRequestBodyProjectDetails projectDetails,
			@ModelAttribute ProjectDetailsRequestBodyProjectDetailsTechnology projectDetailsRequestBodyTechnology,
			@ModelAttribute ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase projectDetailsRequestBodyTechnologyDatabase,
			@ModelAttribute ProjectTeamDetailsRequestBody projectTeamDetailsRequestBody,
			@ModelAttribute ProjectTeamDetailsRequestBodyTeamMembersDetails projectTeamDetailsRequestBodyTeamMembersDetails,
			HttpServletRequest request) throws Exception {

		int counterDatabaseNamesArray = 0;
		int counterTeamMembersArray = 0;
		String[] valuesArray = null;
		
		

		List<ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase> projectDetailsRequestBodyTechnologyDatabaseList = new ArrayList<>();
		List<ProjectTeamDetailsRequestBodyTeamMembersDetails> projectTeamDetailsRequestBodyTeamMembersDetailsList = new ArrayList<>();
		ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase projectDetailsRequestBodyTechnologyDatabaseNames = new ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase();

		String[] databaseNamesArray = projectDetailsRequestBodyTechnologyDatabase.getDatabaseName().split(",");
		do {

			projectDetailsRequestBodyTechnologyDatabaseNames
					.setDatabaseName(databaseNamesArray[counterDatabaseNamesArray]);
			projectDetailsRequestBodyTechnologyDatabaseList.add(projectDetailsRequestBodyTechnologyDatabaseNames);
			counterDatabaseNamesArray++;
		} while (counterDatabaseNamesArray < databaseNamesArray.length);
		projectDetailsRequestBodyTechnology.setDatabase(projectDetailsRequestBodyTechnologyDatabaseList);

		projectDetailsRequestBodyTechnology.setProjectType(
				ProjectTypeEnum.valueOf(projectDetailsRequestBodyTechnology.getProjectType().toString().toUpperCase()));

		Class<?> className = projectTeamDetailsRequestBodyTeamMembersDetails.getClass();
		do {
			ProjectTeamDetailsRequestBodyTeamMembersDetails projectTeamDetailsTeamMembersDetails = new ProjectTeamDetailsRequestBodyTeamMembersDetails();
			for (Field field : className.getDeclaredFields()) {
				field.setAccessible(true);
				String fieldName = field.getName();
				Object object = field.get(projectTeamDetailsRequestBodyTeamMembersDetails);
				if (fieldName != "position") {
					valuesArray = object.toString().split(",");

					setField(projectTeamDetailsTeamMembersDetails, fieldName, valuesArray[counterTeamMembersArray]);
				} else {
					valuesArray = object.toString().split(",");
					String value = valuesArray[counterTeamMembersArray].toUpperCase();
					setField(projectTeamDetailsTeamMembersDetails, fieldName, PositionEnum.valueOf(value));
				}

			}
			

			projectTeamDetailsRequestBodyTeamMembersDetailsList.add(projectTeamDetailsTeamMembersDetails);
			counterTeamMembersArray++;
		} while (counterTeamMembersArray < valuesArray.length);
		projectTeamDetailsRequestBody.setTeamMembersDetails(projectTeamDetailsRequestBodyTeamMembersDetailsList);
		projectDetails.setTeamDetails(projectTeamDetailsRequestBody);
		projectDetails.setStatus(com.nablainfotech.resourceService.model.ProjectDetailsRequestBodyProjectDetails.StatusEnum
				.valueOf(projectDetails.getStatus().toString().toUpperCase()));
		projectDetails.setTechnology(projectDetailsRequestBodyTechnology);
		ProjectDetailsRequestBody projectDetailsRequestBody = new ProjectDetailsRequestBody();
		projectDetailsRequestBody.setProjectDetails(projectDetails);
		ApiResponseSuccessProject addProjectDetails = projectManagementServiceFeign
				.updateProjectDetails(projectDetails.getProjectId(), projectDetailsRequestBody);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/home");
		return redirectView;
	}

	/**
	 * Sets a field value on a given object
	 *
	 * @param targetObject the object to set the field value on
	 * @param fieldName    exact name of the field
	 * @param fieldValue   value to set on the field
	 * @return true if the value was successfully set, false otherwise
	 */
	public static boolean setField(Object targetObject, String fieldName, Object fieldValue) {
		Field field;
		try {
			field = targetObject.getClass().getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			field = null;
		}
		Class superClass = targetObject.getClass().getSuperclass();
		while (field == null && superClass != null) {
			try {
				field = superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				superClass = superClass.getSuperclass();
			}
		}
		if (field == null) {
			return false;
		}
		field.setAccessible(true);
		try {
			field.set(targetObject, fieldValue);
			return true;
		} catch (IllegalAccessException e) {
			return false;
		}
	}

	@GetMapping("/findById/{projectId}")
	@ResponseBody
	public String findById(@PathVariable("projectId") String projectId) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		ProjectDetailsResponseBody projectDetailsResponseBody = new ProjectDetailsResponseBody();

		ApiResponseSuccessProject projectDetails = projectManagementServiceFeign.getProjectDetails(projectId, 0, 1);
		List<ResponseSuccessProject> results = projectDetails.getResults();
		for (ResponseSuccessProject responseSuccessProject : results) {
			projectDetailsResponseBody = responseSuccessProject.getProjectDetails();

		}
		String jsonProjectDetails = objectMapper.writeValueAsString(projectDetailsResponseBody);
		return jsonProjectDetails;
	}

	@RequestMapping("/update/{projectId}")
	public String UpdateProjectDetails(@PathVariable("projectId") String projectId, HttpServletRequest request,
			Model model) {
		ApiResponseSuccessProject projectDetails = projectManagementServiceFeign.getProjectDetails(projectId, 0, 1);
		List<ResponseSuccessProject> results = projectDetails.getResults();
		ProjectDetailsResponseBody projectDetailsResponseBody = new ProjectDetailsResponseBody();
		for (ResponseSuccessProject responseSuccessProject : results) {
			projectDetailsResponseBody = responseSuccessProject.getProjectDetails();

		}
		List<Integer> rangeYearsOfExperience = IntStream.rangeClosed(1, 30)
			    .boxed().collect(Collectors.toList());
		model.addAttribute("projectDetails", projectDetailsResponseBody);
		model.addAttribute("statusEnum", StatusEnum.values());
		model.addAttribute("projectType", ProjectTypeEnum.values());
		model.addAttribute("position", PositionEnum.values());
		model.addAttribute("rangeYearsOfExperience", rangeYearsOfExperience);
		return "UpdateForm";
	}

}
