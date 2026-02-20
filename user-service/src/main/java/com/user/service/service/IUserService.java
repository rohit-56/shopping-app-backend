package com.user.service.service;


import com.user.service.dto.LoginRequest;
import com.user.service.dto.UserEntityRequest;
import com.user.service.dto.UserEntityResponse;

public interface IUserService {

  public UserEntityResponse createUser(UserEntityRequest userEntityRequest);

  public UserEntityResponse getUserById(Long userId);

  public boolean validateUser(LoginRequest loginRequest);
}
