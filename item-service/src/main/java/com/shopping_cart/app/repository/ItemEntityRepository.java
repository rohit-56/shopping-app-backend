package com.shopping_cart.app.repository;

import com.shopping_cart.app.common.enums.CategoryType;
import com.shopping_cart.app.model.ItemEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {

  List<ItemEntity> findByCategoryType(CategoryType categoryType);
}
