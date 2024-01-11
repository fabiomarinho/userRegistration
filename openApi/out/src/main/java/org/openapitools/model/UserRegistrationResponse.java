package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UserRegistrationResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-11T00:12:03.300940424Z[Etc/UTC]")
public class UserRegistrationResponse {

  private String message;

  private String uuid;

  public UserRegistrationResponse message(String message) {
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

  public UserRegistrationResponse uuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

  /**
   * Get uuid
   * @return uuid
  */
  
  @Schema(name = "uuid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("uuid")
  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserRegistrationResponse userRegistrationResponse = (UserRegistrationResponse) o;
    return Objects.equals(this.message, userRegistrationResponse.message) &&
        Objects.equals(this.uuid, userRegistrationResponse.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, uuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserRegistrationResponse {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

