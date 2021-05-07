package com.projectmanagement.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectmanagement.model.ProjectDetailsResponseBody;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ResponseSuccessProject
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")

public class ResponseSuccessProject {
	@JsonProperty("projectDetails")
	private ProjectDetailsResponseBody projectDetails = null;

	public ResponseSuccessProject projectDetails(ProjectDetailsResponseBody projectDetails) {
		this.projectDetails = projectDetails;
		return this;
	}

	/**
	 * Get projectDetails
	 * 
	 * @return projectDetails
	 **/
	@Schema(description = "")

	@Valid
	public ProjectDetailsResponseBody getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(ProjectDetailsResponseBody projectDetails) {
		this.projectDetails = projectDetails;
	}

	public ResponseSuccessProject(ProjectDetailsResponseBody projectDetails) {
		super();
		this.projectDetails = projectDetails;
	}

	public ResponseSuccessProject() {

	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResponseSuccessProject responseSuccessProject = (ResponseSuccessProject) o;
		return Objects.equals(this.projectDetails, responseSuccessProject.projectDetails);
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectDetails);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResponseSuccessProject {\n");

		sb.append("    projectDetails: ").append(toIndentedString(projectDetails)).append("\n");
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
