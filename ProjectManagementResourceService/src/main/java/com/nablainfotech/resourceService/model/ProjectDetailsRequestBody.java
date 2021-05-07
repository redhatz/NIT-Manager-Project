package com.nablainfotech.resourceService.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.nablainfotech.resourceService.model.ProjectDetailsRequestBodyProjectDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProjectDetailsRequestBody
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")


public class ProjectDetailsRequestBody   {
  @JsonProperty("projectDetails")
  private ProjectDetailsRequestBodyProjectDetails projectDetails = null;

  public ProjectDetailsRequestBody projectDetails(ProjectDetailsRequestBodyProjectDetails projectDetails) {
    this.projectDetails = projectDetails;
    return this;
  }

  /**
   * Get projectDetails
   * @return projectDetails
   **/
  @Schema(description = "")
  
    @Valid
    public ProjectDetailsRequestBodyProjectDetails getProjectDetails() {
    return projectDetails;
  }

  public void setProjectDetails(ProjectDetailsRequestBodyProjectDetails projectDetails) {
    this.projectDetails = projectDetails;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDetailsRequestBody projectDetailsRequestBody = (ProjectDetailsRequestBody) o;
    return Objects.equals(this.projectDetails, projectDetailsRequestBody.projectDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDetailsRequestBody {\n");
    
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
