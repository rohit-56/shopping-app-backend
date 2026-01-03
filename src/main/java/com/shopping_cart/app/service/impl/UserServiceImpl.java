package com.shopping_cart.app.service.impl;

import com.shopping_cart.app.common.mapper.UserEntityMapper;
import com.shopping_cart.app.http.request.LoginRequest;
import com.shopping_cart.app.http.request.UserEntityRequest;
import com.shopping_cart.app.http.response.UserEntityResponse;
import com.shopping_cart.app.model.UserEntity;
import com.shopping_cart.app.repository.UserEntityRepository;
import com.shopping_cart.app.service.IUserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  private UserEntityRepository userEntityRepository;

  public UserServiceImpl(UserEntityRepository userEntityRepository) {
    this.userEntityRepository = userEntityRepository;
  }

  @Override
  public UserEntityResponse createUser(UserEntityRequest userEntityRequest) {
    UserEntity userEntity =
        UserEntityMapper.fromUserEntityRequestToUserEntity.apply(userEntityRequest);

    // To Do -- Encrypt Password

    UserEntity savedUser = userEntityRepository.save(userEntity);
    UserEntityResponse userEntityResponse =
        UserEntityMapper.fromUserEntityToUserEntityResponse.apply(savedUser);
    return userEntityResponse;
  }

  @Override
  public UserEntityResponse getUserById(Long userId) {
    Optional<UserEntity> userEntity = userEntityRepository.findById(userId);
    if (userEntity.get() == null) {
      return null;
    } else {
      UserEntityResponse userEntityResponse =
          UserEntityMapper.fromUserEntityToUserEntityResponse.apply(userEntity.get());
      return userEntityResponse;
    }
  }

  @Override
  public boolean validateUser(LoginRequest loginRequest) {
    Optional<UserEntity> userEntity =
        userEntityRepository.findByUsername(loginRequest.getUsername());

    if (userEntity.isEmpty()) {
      return false;
    }

    // To Do -- Password validation encryption

    if (!userEntity.get().getPassword().equals(loginRequest.getPassword())) {
      return false;
    }

    return true;
  }
}
