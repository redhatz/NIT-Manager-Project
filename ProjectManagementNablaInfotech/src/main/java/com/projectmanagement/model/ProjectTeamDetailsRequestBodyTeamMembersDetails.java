package com.projectmanagement.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProjectTeamDetailsRequestBodyTeamMembersDetails
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")


public class ProjectTeamDetailsRequestBodyTeamMembersDetails   {
  @JsonProperty("employeeFullName")
  private String employeeFullName = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("middleName")
  private String middleName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("mobileNumber")
  private String mobileNumber = null;

  /**
   * Position of the employee he/she is in
   */
  public enum PositionEnum {
    INTERN("intern"),
    
    TRAINEE("trainee"),
    
    TESTER("tester"),
    
    DEVEOPER("Deveoper"),
    
    SENIOR_SOFTWARE_DEVELOPER("senior software developer"),
    
    OTHER("other");

    private String value;

    PositionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PositionEnum fromValue(String text) {
      for (PositionEnum b : PositionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("position")
  private PositionEnum position = null;

  @JsonProperty("yearsOfExperience")
  private String yearsOfExperience = null;

  public ProjectTeamDetailsRequestBodyTeamMembersDetails employeeFullName(String employeeFullName) {
    this.employeeFullName = employeeFullName;
    return this;
  }

  /**
   * Full name of the Employee
   * @return employeeFullName
   **/
  @Schema(example = "Prasanjeet Prodip Sinha", description = "Full name of the Employee")
  
    public String getEmployeeFullName() {
    return employeeFullName;
  }

  public void setEmployeeFullName(String employeeFullName) {
    this.employeeFullName = employeeFullName;
  }

  public ProjectTeamDetailsRequestBodyTeamMembersDetails firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * First name of the Employee
   * @return firstName
   **/
  @Schema(example = "Prasanjeet", description = "First name of the Employee")
  
    public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public ProjectTeamDetailsRequestBodyTeamMembersDetails middleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  /**
   * Middle name of the Employee
   * @return middleName
   **/
  @Schema(example = "Prodip", description = "Middle name of the Employee")
  
    public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public ProjectTeamDetailsRequestBodyTeamMembersDetails lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Last name of the Employee
   * @return lastName
   **/
  @Schema(example = "Sinha", description = "Last name of the Employee")
  
    public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public ProjectTeamDetailsRequestBodyTeamMembersDetails mobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  /**
   * Mobile number of the employee is case of emergency
   * @return mobileNumber
   **/
  @Schema(example = "8788894354", description = "Mobile number of the employee is case of emergency")
  
    public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public ProjectTeamDetailsRequestBodyTeamMembersDetails position(PositionEnum position) {
    this.position = position;
    return this;
  }

  /**
   * Position of the employee he/she is in
   * @return position
   **/
  @Schema(example = "intern", description = "Position of the employee he/she is in")
  
    public PositionEnum getPosition() {
    return position;
  }

  public void setPosition(PositionEnum position) {
    this.position = position;
  }

  public ProjectTeamDetailsRequestBodyTeamMembersDetails yearsOfExperience(String yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience;
    return this;
  }

  /**
   * Years of experience the employee is having
   * @return yearsOfExperience
   **/
  @Schema(example = "1 yr", description = "Years of experience the employee is having")
  
    public String getYearsOfExperience() {
    return yearsOfExperience;
  }

  public void setYearsOfExperience(String yearsOfExperience) {
    this.yearsOfExperience = yearsOfExperience;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectTeamDetailsRequestBodyTeamMembersDetails projectTeamDetailsRequestBodyTeamMembersDetails = (ProjectTeamDetailsRequestBodyTeamMembersDetails) o;
    return Objects.equals(this.employeeFullName, projectTeamDetailsRequestBodyTeamMembersDetails.employeeFullName) &&
        Objects.equals(this.firstName, projectTeamDetailsRequestBodyTeamMembersDetails.firstName) &&
        Objects.equals(this.middleName, projectTeamDetailsRequestBodyTeamMembersDetails.middleName) &&
        Objects.equals(this.lastName, projectTeamDetailsRequestBodyTeamMembersDetails.lastName) &&
        Objects.equals(this.mobileNumber, projectTeamDetailsRequestBodyTeamMembersDetails.mobileNumber) &&
        Objects.equals(this.position, projectTeamDetailsRequestBodyTeamMembersDetails.position) &&
        Objects.equals(this.yearsOfExperience, projectTeamDetailsRequestBodyTeamMembersDetails.yearsOfExperience);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employeeFullName, firstName, middleName, lastName, mobileNumber, position, yearsOfExperience);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectTeamDetailsRequestBodyTeamMembersDetails {\n");
    
    sb.append("    employeeFullName: ").append(toIndentedString(employeeFullName)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    mobileNumber: ").append(toIndentedString(mobileNumber)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    yearsOfExperience: ").append(toIndentedString(yearsOfExperience)).append("\n");
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
