package com.projectmanagement.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectmanagement.model.ProjectTeamDetailsRequestBodyTeamMembersDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Team Details.
 */
@Schema(description = "Team Details.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")


public class ProjectTeamDetailsRequestBody   {
  @JsonProperty("teamId")
  private String teamId = null;

  @JsonProperty("projectLeader")
  private String projectLeader = null;

  @JsonProperty("projectAssigned")
  private String projectAssigned = null;

  @JsonProperty("teamMembersDetails")
  @Valid
  private List<ProjectTeamDetailsRequestBodyTeamMembersDetails> teamMembersDetails = new ArrayList<ProjectTeamDetailsRequestBodyTeamMembersDetails>();

  public ProjectTeamDetailsRequestBody teamId(String teamId) {
    this.teamId = teamId;
    return this;
  }

  /**
   * Unique Id to identify the team
   * @return teamId
   **/
  @Schema(example = "team4", description = "Unique Id to identify the team")
  
    public String getTeamId() {
    return teamId;
  }

  public void setTeamId(String teamId) {
    this.teamId = teamId;
  }

  public ProjectTeamDetailsRequestBody projectLeader(String projectLeader) {
    this.projectLeader = projectLeader;
    return this;
  }

  /**
   * Project Leader
   * @return projectLeader
   **/
  @Schema(example = "Abhijit", required = true, description = "Project Leader")
      

    public String getProjectLeader() {
    return projectLeader;
  }

  public void setProjectLeader(String projectLeader) {
    this.projectLeader = projectLeader;
  }

  public ProjectTeamDetailsRequestBody projectAssigned(String projectAssigned) {
    this.projectAssigned = projectAssigned;
    return this;
  }

  /**
   * Project realted to career management
   * @return projectAssigned
   **/
  @Schema(example = "Career Management API", required = true, description = "Project realted to career management")
      

    public String getProjectAssigned() {
    return projectAssigned;
  }

  public void setProjectAssigned(String projectAssigned) {
    this.projectAssigned = projectAssigned;
  }

  public ProjectTeamDetailsRequestBody teamMembersDetails(List<ProjectTeamDetailsRequestBodyTeamMembersDetails> teamMembersDetails) {
    this.teamMembersDetails = teamMembersDetails;
    return this;
  }

  public ProjectTeamDetailsRequestBody addTeamMembersDetailsItem(ProjectTeamDetailsRequestBodyTeamMembersDetails teamMembersDetailsItem) {
    this.teamMembersDetails.add(teamMembersDetailsItem);
    return this;
  }

  /**
   * Get teamMembersDetails
   * @return teamMembersDetails
   **/
  @Schema(required = true, description = "")
      
    @Valid
    public List<ProjectTeamDetailsRequestBodyTeamMembersDetails> getTeamMembersDetails() {
    return teamMembersDetails;
  }

  public void setTeamMembersDetails(List<ProjectTeamDetailsRequestBodyTeamMembersDetails> teamMembersDetails) {
    this.teamMembersDetails = teamMembersDetails;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectTeamDetailsRequestBody projectTeamDetailsRequestBody = (ProjectTeamDetailsRequestBody) o;
    return Objects.equals(this.teamId, projectTeamDetailsRequestBody.teamId) &&
        Objects.equals(this.projectLeader, projectTeamDetailsRequestBody.projectLeader) &&
        Objects.equals(this.projectAssigned, projectTeamDetailsRequestBody.projectAssigned) &&
        Objects.equals(this.teamMembersDetails, projectTeamDetailsRequestBody.teamMembersDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teamId, projectLeader, projectAssigned, teamMembersDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectTeamDetailsRequestBody {\n");
    
    sb.append("    teamId: ").append(toIndentedString(teamId)).append("\n");
    sb.append("    projectLeader: ").append(toIndentedString(projectLeader)).append("\n");
    sb.append("    projectAssigned: ").append(toIndentedString(projectAssigned)).append("\n");
    sb.append("    teamMembersDetails: ").append(toIndentedString(teamMembersDetails)).append("\n");
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
