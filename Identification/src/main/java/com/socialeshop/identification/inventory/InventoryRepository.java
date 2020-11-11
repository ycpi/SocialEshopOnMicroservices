package com.socialeshop.identification.repositories;

import com.socialeshop.identification.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
