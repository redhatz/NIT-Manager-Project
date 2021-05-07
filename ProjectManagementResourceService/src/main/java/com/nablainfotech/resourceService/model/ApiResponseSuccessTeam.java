package com.nablainfotech.resourceService.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nablainfotech.resourceService.model.Pagination;
import com.nablainfotech.resourceService.model.ResponseSuccessTeam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Success response for team related operations
 */
@Schema(description = "Success response for team related operations")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")

@JsonInclude(Include.NON_NULL)
public class ApiResponseSuccessTeam   {
  @JsonProperty("statusCode")
  private Integer statusCode = null;

  @JsonProperty("statusMessage")
  private String statusMessage = null;

  @JsonProperty("pagination")
  private Pagination pagination = null;

  @JsonProperty("results")
  @Valid
  private List<ResponseSuccessTeam> results = null;

  @JsonProperty("errorMessages")
  @Valid
  private List<String> errorMessages = null;

  @JsonProperty("messages")
  @Valid
  private List<String> messages = null;

  public ApiResponseSuccessTeam statusCode(Integer statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  /**
   * Get statusCode
   * @return statusCode
   **/
  @Schema(description = "")
  
    public Integer getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public ApiResponseSuccessTeam statusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
    return this;
  }

  /**
   * Get statusMessage
   * @return statusMessage
   **/
  @Schema(description = "")
  
    public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  public ApiResponseSuccessTeam pagination(Pagination pagination) {
    this.pagination = pagination;
    return this;
  }

  /**
   * Get pagination
   * @return pagination
   **/
  @Schema(description = "")
  
    @Valid
    public Pagination getPagination() {
    return pagination;
  }

  public void setPagination(Pagination pagination) {
    this.pagination = pagination;
  }

  public ApiResponseSuccessTeam results(List<ResponseSuccessTeam> results) {
    this.results = results;
    return this;
  }

  public ApiResponseSuccessTeam addResultsItem(ResponseSuccessTeam resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<ResponseSuccessTeam>();
    }
    this.results.add(resultsItem);
    return this;
  }

  /**
   * Get results
   * @return results
   **/
  @Schema(description = "")
      @Valid
    public List<ResponseSuccessTeam> getResults() {
    return results;
  }

  public void setResults(List<ResponseSuccessTeam> results) {
    this.results = results;
  }

  public ApiResponseSuccessTeam errorMessages(List<String> errorMessages) {
    this.errorMessages = errorMessages;
    return this;
  }

  public ApiResponseSuccessTeam addErrorMessagesItem(String errorMessagesItem) {
    if (this.errorMessages == null) {
      this.errorMessages = new ArrayList<String>();
    }
    this.errorMessages.add(errorMessagesItem);
    return this;
  }

  /**
   * Get errorMessages
   * @return errorMessages
   **/
  @Schema(description = "")
  
    public List<String> getErrorMessages() {
    return errorMessages;
  }

  public void setErrorMessages(List<String> errorMessages) {
    this.errorMessages = errorMessages;
  }

  public ApiResponseSuccessTeam messages(List<String> messages) {
    this.messages = messages;
    return this;
  }

  public ApiResponseSuccessTeam addMessagesItem(String messagesItem) {
    if (this.messages == null) {
      this.messages = new ArrayList<String>();
    }
    this.messages.add(messagesItem);
    return this;
  }

  /**
   * Get messages
   * @return messages
   **/
  @Schema(description = "")
  
    public List<String> getMessages() {
    return messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiResponseSuccessTeam apiResponseSuccessTeam = (ApiResponseSuccessTeam) o;
    return Objects.equals(this.statusCode, apiResponseSuccessTeam.statusCode) &&
        Objects.equals(this.statusMessage, apiResponseSuccessTeam.statusMessage) &&
        Objects.equals(this.pagination, apiResponseSuccessTeam.pagination) &&
        Objects.equals(this.results, apiResponseSuccessTeam.results) &&
        Objects.equals(this.errorMessages, apiResponseSuccessTeam.errorMessages) &&
        Objects.equals(this.messages, apiResponseSuccessTeam.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode, statusMessage, pagination, results, errorMessages, messages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiResponseSuccessTeam {\n");
    
    sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
    sb.append("    statusMessage: ").append(toIndentedString(statusMessage)).append("\n");
    sb.append("    pagination: ").append(toIndentedString(pagination)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
    sb.append("    errorMessages: ").append(toIndentedString(errorMessages)).append("\n");
    sb.append("    messages: ").append(toIndentedString(messages)).append("\n");
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
