package com.user.service.dto;


public class UserEntityResponse {

  public Long Id;

  public String name;

  public String email;

  public String location;

  public String username;

  public long number;

  public void setId(Long id) {
    Id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setNumber(long number) {
    this.number = number;
  }
}
