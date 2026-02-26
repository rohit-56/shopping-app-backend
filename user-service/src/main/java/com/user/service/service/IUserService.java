package com.user.service.service;


import com.user.service.dto.LoginRequest;
import com.user.service.dto.UserEntityRequest;
import com.user.service.dto.UserEntityResponse;

public interface IUserService {

  public UserEntityResponse createUser(UserEntityRequest userEntityRequest);

  public UserEntityResponse getUserById(Long userId);

  public String validateUserAndGetToken(LoginRequest loginRequest);

  public UserEntityResponse getUserByEmail(String email);

  public boolean validateToken(String token);
}

