package com.shopping_cart.app.repository;

import com.shopping_cart.app.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {

    public Optional<UserEntity> findByUsername(String username);
}
