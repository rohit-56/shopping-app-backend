package com.user.service.repository;


import java.util.Optional;

import com.user.service.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

  public Optional<UserEntity> findByUsername(String username);
}
