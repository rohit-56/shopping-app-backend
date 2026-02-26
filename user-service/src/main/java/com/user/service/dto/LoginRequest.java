package com.user.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

  @NotBlank(message = "Email is Required")
  @Email(message = "Enter a valid email address")
  public String email;

  @NotBlank(message = "Password is Required")
  public String password;

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
