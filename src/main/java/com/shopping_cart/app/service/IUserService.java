package com.shopping_cart.app.service;

import com.shopping_cart.app.http.request.LoginRequest;
import com.shopping_cart.app.http.request.UserEntityRequest;
import com.shopping_cart.app.http.response.UserEntityResponse;

public interface IUserService {

  public UserEntityResponse createUser(UserEntityRequest userEntityRequest);

  public UserEntityResponse getUserById(Long userId);

  public boolean validateUser(LoginRequest loginRequest);
}
