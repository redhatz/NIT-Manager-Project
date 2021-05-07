package com.projectmanagement.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectmanagement.model.ProjectTeamDetailsResponseBody;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ResponseSuccessTeam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")


public class ResponseSuccessTeam   {
  @JsonProperty("teamDetails")
  private ProjectTeamDetailsResponseBody teamDetails = null;

  public ResponseSuccessTeam teamDetails(ProjectTeamDetailsResponseBody teamDetails) {
    this.teamDetails = teamDetails;
    return this;
  }

  /**
   * Get teamDetails
   * @return teamDetails
   **/
  @Schema(description = "")
  
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
    ResponseSuccessTeam responseSuccessTeam = (ResponseSuccessTeam) o;
    return Objects.equals(this.teamDetails, responseSuccessTeam.teamDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(teamDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseSuccessTeam {\n");
    
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
