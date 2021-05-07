package com.nablainfotech.resourceService.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nablainfotech.resourceService.model.ProjectDetailsRequestBodyProjectDetailsTechnology;
import com.nablainfotech.resourceService.model.ProjectTeamDetailsResponseBody;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProjectDetailsResponseBody
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")

@JsonInclude(Include.NON_NULL)
public class ProjectDetailsResponseBody  implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@JsonProperty("projectId")
  private String projectId = null;

  @JsonProperty("projectName")
  private String projectName = null;

  @JsonProperty("projectDescription")
  private String projectDescription = null;

  @JsonProperty("projectOwner")
  private String projectOwner = null;

  /**
   * status to know if the project is in working stage,completed or stopped
   */
  public enum StatusEnum {
    WORKING("working"),
    
    COMPLETED("completed"),
    
    STOPPED("stopped");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("startDate")
  private String startDate = null;

  @JsonProperty("endDate")
  private String endDate = null;

  @JsonProperty("modifiedDate")
  private String modifiedDate = null;

  @JsonProperty("teamSize")
  private BigDecimal teamSize = null;

  @JsonProperty("technology")
  private ProjectDetailsRequestBodyProjectDetailsTechnology technology = null;

  @JsonProperty("teamDetails")
  private ProjectTeamDetailsResponseBody teamDetails = null;

  public ProjectDetailsResponseBody projectId(String projectId) {
    this.projectId = projectId;
    return this;
  }

  /**
   * Unique Id to identify the project
   * @return projectId
   **/
  @Schema(example = "123456", description = "Unique Id to identify the project")
  
    public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public ProjectDetailsResponseBody projectName(String projectName) {
    this.projectName = projectName;
    return this;
  }

  /**
   * Name of the project
   * @return projectName
   **/
  @Schema(example = "Intern Management System", required = true, description = "Name of the project")
      @NotNull

    public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public ProjectDetailsResponseBody projectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
    return this;
  }

  /**
   * Description about the project
   * @return projectDescription
   **/
  @Schema(example = "Project to Manage Interns", required = true, description = "Description about the project")
      @NotNull

    public String getProjectDescription() {
    return projectDescription;
  }

  public void setProjectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
  }

  public ProjectDetailsResponseBody projectOwner(String projectOwner) {
    this.projectOwner = projectOwner;
    return this;
  }

  /**
   * Owner of the project
   * @return projectOwner
   **/
  @Schema(example = "Google", required = true, description = "Owner of the project")
      @NotNull

    public String getProjectOwner() {
    return projectOwner;
  }

  public void setProjectOwner(String projectOwner) {
    this.projectOwner = projectOwner;
  }

  public ProjectDetailsResponseBody status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * status to know if the project is in working stage,completed or stopped
   * @return status
   **/
  @Schema(example = "completed", required = true, description = "status to know if the project is in working stage,completed or stopped")
      @NotNull

    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public ProjectDetailsResponseBody startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * The date on which the project is started
   * @return startDate
   **/
  @Schema(example = "30 January 2021", required = true, description = "The date on which the project is started")
      @NotNull

    public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public ProjectDetailsResponseBody endDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * deadline of the project
   * @return endDate
   **/
  @Schema(example = "1 March 2021", required = true, description = "deadline of the project")
      @NotNull

    public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public ProjectDetailsResponseBody modifiedDate(String modifiedDate) {
    this.modifiedDate = modifiedDate;
    return this;
  }

  /**
   * The date on which project details is modified
   * @return modifiedDate
   **/
  @Schema(example = "22 February 2021", required = true, description = "The date on which project details is modified")
      @NotNull

    public String getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(String modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public ProjectDetailsResponseBody teamSize(BigDecimal teamSize) {
    this.teamSize = teamSize;
    return this;
  }

  /**
   * Number of members in the team
   * @return teamSize
   **/
  @Schema(example = "5", required = true, description = "Number of members in the team")
      @NotNull

    @Valid
    public BigDecimal getTeamSize() {
    return teamSize;
  }

  public void setTeamSize(BigDecimal teamSize) {
    this.teamSize = teamSize;
  }

  public ProjectDetailsResponseBody technology(ProjectDetailsRequestBodyProjectDetailsTechnology technology) {
    this.technology = technology;
    return this;
  }

  /**
   * Get technology
   * @return technology
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ProjectDetailsRequestBodyProjectDetailsTechnology getTechnology() {
    return technology;
  }

  public void setTechnology(ProjectDetailsRequestBodyProjectDetailsTechnology technology) {
    this.technology = technology;
  }

  public ProjectDetailsResponseBody teamDetails(ProjectTeamDetailsResponseBody teamDetails) {
    this.teamDetails = teamDetails;
    return this;
  }

  /**
   * Get teamDetails
   * @return teamDetails
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ProjectTeamDetailsResponseBody getTeamDetails() {
    return teamDetails;
  }

  public void setTeamDetails(ProjectTeamDetailsResponseBody teamDetails) {
    this.teamDetails = teamDetails;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDetailsResponseBody projectDetailsResponseBody = (ProjectDetailsResponseBody) o;
    return Objects.equals(this.projectId, projectDetailsResponseBody.projectId) &&
        Objects.equals(this.projectName, projectDetailsResponseBody.projectName) &&
        Objects.equals(this.projectDescription, projectDetailsResponseBody.projectDescription) &&
        Objects.equals(this.projectOwner, projectDetailsResponseBody.projectOwner) &&
        Objects.equals(this.status, projectDetailsResponseBody.status) &&
        Objects.equals(this.startDate, projectDetailsResponseBody.startDate) &&
        Objects.equals(this.endDate, projectDetailsResponseBody.endDate) &&
        Objects.equals(this.modifiedDate, projectDetailsResponseBody.modifiedDate) &&
        Objects.equals(this.teamSize, projectDetailsResponseBody.teamSize) &&
        Objects.equals(this.technology, projectDetailsResponseBody.technology) &&
        Objects.equals(this.teamDetails, projectDetailsResponseBody.teamDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, projectName, projectDescription, projectOwner, status, startDate, endDate, modifiedDate, teamSize, technology, teamDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDetailsResponseBody {\n");
    
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
    sb.append("    projectDescription: ").append(toIndentedString(projectDescription)).append("\n");
    sb.append("    projectOwner: ").append(toIndentedString(projectOwner)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    modifiedDate: ").append(toIndentedString(modifiedDate)).append("\n");
    sb.append("    teamSize: ").append(toIndentedString(teamSize)).append("\n");
    sb.append("    technology: ").append(toIndentedString(technology)).append("\n");
    sb.append("    teamDetails: ").append(toIndentedString(teamDetails)).append("\n");
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
