package com.socialeshop.api.repositories;

import com.socialeshop.api.core.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
