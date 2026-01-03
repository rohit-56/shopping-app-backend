package com.shopping_cart.app.common.mapper;

import com.shopping_cart.app.http.request.UserEntityRequest;
import com.shopping_cart.app.http.response.UserEntityResponse;
import com.shopping_cart.app.model.UserEntity;
import java.util.function.Function;

public class UserEntityMapper {

  public static final Function<UserEntityRequest, UserEntity> fromUserEntityRequestToUserEntity =
      userEntityRequest -> {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userEntityRequest.getName());
        userEntity.setEmail(userEntityRequest.getEmail());
        userEntity.setUsername(userEntityRequest.getUsername());
        userEntity.setLocation(userEntityRequest.getLocation());
        userEntity.setPassword(userEntityRequest.getPassword());
        userEntity.setNumber(userEntityRequest.getNumber());
        return userEntity;
      };

  public static final Function<UserEntity, UserEntityResponse> fromUserEntityToUserEntityResponse =
      userEntity -> {
        UserEntityResponse userEntityResponse = new UserEntityResponse();
        userEntityResponse.setId(userEntity.getId());
        userEntityResponse.setName(userEntity.getName());
        userEntityResponse.setEmail(userEntity.getEmail());
        userEntityResponse.setUsername(userEntity.getUsername());
        userEntityResponse.setNumber(userEntity.getNumber());
        userEntityResponse.setLocation(userEntity.getLocation());
        return userEntityResponse;
      };
}
