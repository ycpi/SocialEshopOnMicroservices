package com.socialeshop.microservices.core.inventory.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    List<InventoryEntity> findByItemName(String itemName);

    List<InventoryEntity> findByTag(String tag);

//    InventoryEntity findByUser(UserEntity itemName);
}
