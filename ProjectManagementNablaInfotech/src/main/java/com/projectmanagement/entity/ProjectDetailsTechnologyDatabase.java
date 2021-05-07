package com.projectmanagement.entity;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")


public class ProjectDetailsTechnologyDatabase   {
  @JsonProperty("databaseName")
  private String databaseName = null;

  public ProjectDetailsTechnologyDatabase databaseName(String databaseName) {
    this.databaseName = databaseName;
    return this;
  }

  /**
   * Name of the database to store the data
   * @return databaseName
   **/
  @Schema(example = "oracle", description = "Name of the database to store the data")
  
    public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDetailsTechnologyDatabase projectDetailsRequestBodyProjectDetailsTechnologyDatabase = (ProjectDetailsTechnologyDatabase) o;
    return Objects.equals(this.databaseName, projectDetailsRequestBodyProjectDetailsTechnologyDatabase.databaseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(databaseName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase {\n");
    
    sb.append("    databaseName: ").append(toIndentedString(databaseName)).append("\n");
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
