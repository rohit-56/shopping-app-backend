package com.user.service.service.impl;

import com.user.service.common.mapper.UserEntityMapper;
import com.user.service.dto.LoginRequest;
import java.util.Optional;

import com.user.service.dto.UserEntityRequest;
import com.user.service.dto.UserEntityResponse;
import com.user.service.model.UserEntity;
import com.user.service.repository.UserEntityRepository;
import com.user.service.service.IUserService;
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

  @Override
  public UserEntityResponse getUserByEmail(String email) {
    return null;
  }
}
