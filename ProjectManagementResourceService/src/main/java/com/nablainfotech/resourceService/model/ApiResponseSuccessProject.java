package com.nablainfotech.resourceService.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nablainfotech.resourceService.model.Pagination;
import com.nablainfotech.resourceService.model.ResponseSuccessProject;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Success response for project details related operations
 */

public class ApiResponseSuccessProject {
	
	private Integer statusCode = null;

	
	private String statusMessage = null;

	
	private Pagination pagination = null;

	
	private List<ResponseSuccessProject> results = null;

	
	private List<String> errorMessages = null;

	public ApiResponseSuccessProject errorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
		return this;
	}

	public ApiResponseSuccessProject addErrorMessagesItem(String errorMessagesItem) {
		if (this.errorMessages == null) {
			this.errorMessages = new ArrayList<String>();
		}
		this.errorMessages.add(errorMessagesItem);
		return this;
	}

	/**
	 * Get errorMessages
	 * 
	 * @return errorMessages
	 **/
	

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public ApiResponseSuccessProject statusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	/**
	 * Get statusCode
	 * 
	 * @return statusCode
	 **/
	

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public ApiResponseSuccessProject statusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
		return this;
	}

	/**
	 * Get statusMessage
	 * 
	 * @return statusMessage
	 **/
	

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public ApiResponseSuccessProject pagination(Pagination pagination) {
		this.pagination = pagination;
		return this;
	}

	/**
	 * Get pagination
	 * 
	 * @return pagination
	 **/
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public ApiResponseSuccessProject results(List<ResponseSuccessProject> results) {
		this.results = results;
		return this;
	}

	public ApiResponseSuccessProject addResultsItem(ResponseSuccessProject resultsItem) {
		if (this.results == null) {
			this.results = new ArrayList<ResponseSuccessProject>();
		}
		this.results.add(resultsItem);
		return this;
	}

	/**
	 * Get results
	 * 
	 * @return results
	 **/
	
	public List<ResponseSuccessProject> getResults() {
		return results;
	}

	public void setResults(List<ResponseSuccessProject> results) {
		this.results = results;
	}
	
	

	/**
	 * Parameterized constructor for success
	 * 
	 * @param statusCode
	 * @param statusMessage
	 * @param pagination
	 * @param results
	 */
	public ApiResponseSuccessProject(Integer statusCode, String statusMessage, Pagination pagination,
			 List<ResponseSuccessProject> results) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.pagination = pagination;
		this.results = results;
	}
	

	/**
	 * Parameterized constructor for any error 
	 * 
	 * @param statusCode
	 * @param statusMessage
	 * @param errorMessages
	 */
	public ApiResponseSuccessProject(Integer statusCode, String statusMessage,  List<String> errorMessages) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.errorMessages = errorMessages;
	}

	public ApiResponseSuccessProject() {
		
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ApiResponseSuccessProject apiResponseSuccessProject = (ApiResponseSuccessProject) o;
		return Objects.equals(this.statusCode, apiResponseSuccessProject.statusCode)
				&& Objects.equals(this.statusMessage, apiResponseSuccessProject.statusMessage)
				&& Objects.equals(this.pagination, apiResponseSuccessProject.pagination)
				&& Objects.equals(this.results, apiResponseSuccessProject.results);
	}

	@Override
	public int hashCode() {
		return Objects.hash(statusCode, statusMessage, pagination, results);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ApiResponseSuccessProject {\n");

		sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
		sb.append("    statusMessage: ").append(toIndentedString(statusMessage)).append("\n");
		sb.append("    pagination: ").append(toIndentedString(pagination)).append("\n");
		sb.append("    results: ").append(toIndentedString(results)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
