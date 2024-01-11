package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


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
  @NotNull(message = "field is required")
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
  @NotNull(message = "field is required")
  @Size(min = 9, message = "Need to be greater than 8 characters") 
  @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[_#$%.])[A-Za-z\\d_#$%.]{9,}$", message = "Should contain at least 1 number, 1 Captialized letter and 1 special character in this set '_ # $ % .'") 
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
  @NotNull(message = "field is required") 
  @Pattern(regexp = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$", message = "the format of the IP must be valid") 
  @Schema(name = "ipAddress", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ipAddress")
  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

}

