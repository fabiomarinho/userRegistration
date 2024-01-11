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
 * UserRegistrationRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-11T00:12:03.300940424Z[Etc/UTC]")
public class UserRegistrationRequest {

  private String username;

  private String password;

  private String ipAddress;

  public UserRegistrationRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UserRegistrationRequest(String username, String password, String ipAddress) {
    this.username = username;
    this.password = password;
    this.ipAddress = ipAddress;
  }

  public UserRegistrationRequest username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */
  @NotNull 
  @Schema(name = "username", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserRegistrationRequest password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  */
  @NotNull @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[_#$%.])[A-Za-z\\d_#$%.]{9,}$") @Size(min = 9) 
  @Schema(name = "password", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserRegistrationRequest ipAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }

  /**
   * Get ipAddress
   * @return ipAddress
  */
  @NotNull @Pattern(regexp = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$") 
  @Schema(name = "ipAddress", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ipAddress")
  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserRegistrationRequest userRegistrationRequest = (UserRegistrationRequest) o;
    return Objects.equals(this.username, userRegistrationRequest.username) &&
        Objects.equals(this.password, userRegistrationRequest.password) &&
        Objects.equals(this.ipAddress, userRegistrationRequest.ipAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, ipAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserRegistrationRequest {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    ipAddress: ").append(toIndentedString(ipAddress)).append("\n");
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

