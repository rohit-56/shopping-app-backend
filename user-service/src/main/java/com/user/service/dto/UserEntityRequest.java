package com.user.service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserEntityRequest {

  @NotBlank(message = "Name is Required")
  public String name;

  @NotBlank(message = "Email is Required")
  @Email(message = "Please Enter valid email address")
  public String email;

  public String location;

  public String username;

  @NotBlank(message = "Password is Required")
  public String password;

  @NotBlank(message = "Number is Required")
  public long number;

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getLocation() {
    return location;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public long getNumber() {
    return number;
  }
}
