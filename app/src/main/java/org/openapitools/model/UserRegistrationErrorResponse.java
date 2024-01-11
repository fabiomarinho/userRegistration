package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * UserRegistrationErrorResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-11T00:12:03.300940424Z[Etc/UTC]")
public class UserRegistrationErrorResponse {

  private String message;

  public UserRegistrationErrorResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  @Schema(name = "message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


}

