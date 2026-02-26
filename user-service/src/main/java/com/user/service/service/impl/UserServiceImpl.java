package com.user.service.service.impl;

import com.user.service.common.enums.USER_ENTITY_ROLE;
import com.user.service.common.mapper.UserEntityMapper;
import com.user.service.dto.LoginRequest;
import java.util.Optional;

import com.user.service.dto.UserEntityRequest;
import com.user.service.dto.UserEntityResponse;
import com.user.service.exception.InvalidPasswordException;
import com.user.service.exception.UserNotFoundException;
import com.user.service.model.UserEntity;
import com.user.service.repository.UserEntityRepository;
import com.user.service.service.IUserService;
import com.user.service.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  private UserEntityRepository userEntityRepository;
  private JwtUtil jwtUtil;
  private PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserEntityRepository userEntityRepository,JwtUtil jwtUtil,PasswordEncoder passwordEncoder) {
    this.userEntityRepository = userEntityRepository;
    this.jwtUtil = jwtUtil;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserEntityResponse createUser(UserEntityRequest userEntityRequest) {
    UserEntity userEntity =
        UserEntityMapper.fromUserEntityRequestToUserEntity.apply(userEntityRequest);

    // To Do -- Encrypt Password
    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

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
  public String validateUserAndGetToken(LoginRequest loginRequest) {
    Optional<UserEntity> userEntity =
        userEntityRepository.findByEmail(loginRequest.getEmail());

    if (userEntity.isEmpty()) {
      throw new UserNotFoundException("User not found");
    }

    // To Do -- Password validation encryption
    String encodedPassword = passwordEncoder.encode(loginRequest.getPassword());
    if (!passwordEncoder.matches(loginRequest.getPassword(), encodedPassword)) {
      throw new InvalidPasswordException("Wrong password");
    }

    return jwtUtil.generateToken(loginRequest.getEmail(), USER_ENTITY_ROLE.ADMIN.getRoleName());
  }

  @Override
  public UserEntityResponse getUserByEmail(String email) {
    Optional<UserEntity> userEntity =
            userEntityRepository.findByEmail(email);

    if (userEntity.isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
    return  UserEntityMapper.fromUserEntityToUserEntityResponse.apply(userEntity.get());
  }

  @Override
  public boolean validateToken(String token) {
    try{
      jwtUtil.validateToken(token);
      return true;
    } catch (RuntimeException e) {
      throw new RuntimeException(e);
    }
  }
}
