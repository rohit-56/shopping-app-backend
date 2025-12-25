package com.shopping_cart.app.repository;

import com.shopping_cart.app.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemEntityRepository extends JpaRepository<ItemEntity,Long> {

    List<ItemEntity> findByCategory(String category);
}
