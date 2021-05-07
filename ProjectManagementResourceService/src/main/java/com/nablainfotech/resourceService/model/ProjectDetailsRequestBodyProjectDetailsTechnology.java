package com.nablainfotech.resourceService.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nablainfotech.resourceService.model.ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Technology used in the project
 */
@Schema(description = "Technology used in the project")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")


public class ProjectDetailsRequestBodyProjectDetailsTechnology   {
  @JsonProperty("language")
  private String language = null;

  @JsonProperty("framework")
  private String framework = null;

  /**
   * Type of the project like Standalone, Web based App, Microservice etc
   */
  public enum ProjectTypeEnum {
    STANDALONE("standalone"),
    
    WEB_BASED_APP("web based app"),
    
    MICROSERVICE("microservice"),
    
    OTHER("other");

    private String value;

    ProjectTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ProjectTypeEnum fromValue(String text) {
      for (ProjectTypeEnum b : ProjectTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("projectType")
  private ProjectTypeEnum projectType = null;

  @JsonProperty("database")
  @Valid
  private List<ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase> database = new ArrayList<ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase>();

  public ProjectDetailsRequestBodyProjectDetailsTechnology language(String language) {
    this.language = language;
    return this;
  }

  /**
   * Programming Language used in the project
   * @return language
   **/
  @Schema(example = "JAVA", required = true, description = "Programming Language used in the project")
      @NotNull

    public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public ProjectDetailsRequestBodyProjectDetailsTechnology framework(String framework) {
    this.framework = framework;
    return this;
  }

  /**
   * Framework used in the project
   * @return framework
   **/
  @Schema(example = "Spring Boot", required = true, description = "Framework used in the project")
      @NotNull

    public String getFramework() {
    return framework;
  }

  public void setFramework(String framework) {
    this.framework = framework;
  }

  public ProjectDetailsRequestBodyProjectDetailsTechnology projectType(ProjectTypeEnum projectType) {
    this.projectType = projectType;
    return this;
  }

  /**
   * Type of the project like Standalone, Web base App, Microservice etc
   * @return projectType
   **/
  @Schema(example = "Microservice", required = true, description = "Type of the project like Standalone, Web base App, Microservice etc")
      @NotNull

    public ProjectTypeEnum getProjectType() {
    return projectType;
  }

  public void setProjectType(ProjectTypeEnum projectType) {
    this.projectType = projectType;
  }

  public ProjectDetailsRequestBodyProjectDetailsTechnology database(List<ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase> database) {
    this.database = database;
    return this;
  }

  public ProjectDetailsRequestBodyProjectDetailsTechnology addDatabaseItem(ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase databaseItem) {
    this.database.add(databaseItem);
    return this;
  }

  /**
   * Get database
   * @return database
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase> getDatabase() {
    return database;
  }

  public void setDatabase(List<ProjectDetailsRequestBodyProjectDetailsTechnologyDatabase> database) {
    this.database = database;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDetailsRequestBodyProjectDetailsTechnology projectDetailsRequestBodyProjectDetailsTechnology = (ProjectDetailsRequestBodyProjectDetailsTechnology) o;
    return Objects.equals(this.language, projectDetailsRequestBodyProjectDetailsTechnology.language) &&
        Objects.equals(this.framework, projectDetailsRequestBodyProjectDetailsTechnology.framework) &&
        Objects.equals(this.projectType, projectDetailsRequestBodyProjectDetailsTechnology.projectType) &&
        Objects.equals(this.database, projectDetailsRequestBodyProjectDetailsTechnology.database);
  }

  @Override
  public int hashCode() {
    return Objects.hash(language, framework, projectType, database);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDetailsRequestBodyProjectDetailsTechnology {\n");
    
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    framework: ").append(toIndentedString(framework)).append("\n");
    sb.append("    projectType: ").append(toIndentedString(projectType)).append("\n");
    sb.append("    database: ").append(toIndentedString(database)).append("\n");
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
