package com.nablainfotech.resourceService.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a paginated search result.
 */
@Schema(description = "Represents a paginated search result.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-22T08:22:53.346Z[GMT]")


public class Pagination   {
  @JsonProperty("offset")
  private Integer offset = 0;

  @JsonProperty("limit")
  private Integer limit = 200;

  @JsonProperty("totalEntries")
  private Integer totalEntries = null;



public Pagination(Integer offset, Integer limit, Integer totalEntries) {
	super();
	this.offset = offset;
	this.limit = limit;
	this.totalEntries = totalEntries;
}



public Pagination() {
}



public Pagination offset(Integer offset) {
    this.offset = offset;
    return this;
  }

  /**
   * The Page from which the result has to be fetched.Page number starts from 0.
   * @return offset
   **/
  @Schema(example = "200", required = true, description = "The Page from which the result has to be fetched.Page number starts from 0.")
      @NotNull

    public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Pagination limit(Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * Number of details or records you want to fetch.
   * @return limit
   **/
  @Schema(example = "20", required = true, description = "Number of details or records you want to fetch.")
      @NotNull

    public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Pagination totalEntries(Integer totalEntries) {
    this.totalEntries = totalEntries;
    return this;
  }

  /**
   * Total number of results in result set.
   * @return totalEntries
   **/
  @Schema(example = "1", required = true, description = "Total number of results in result set.")
      @NotNull

    public Integer getTotalEntries() {
    return totalEntries;
  }

  public void setTotalEntries(Integer totalEntries) {
    this.totalEntries = totalEntries;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pagination pagination = (Pagination) o;
    return Objects.equals(this.offset, pagination.offset) &&
        Objects.equals(this.limit, pagination.limit) &&
        Objects.equals(this.totalEntries, pagination.totalEntries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offset, limit, totalEntries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pagination {\n");
    
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    totalEntries: ").append(toIndentedString(totalEntries)).append("\n");
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
