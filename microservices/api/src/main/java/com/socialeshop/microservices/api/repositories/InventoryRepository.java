package com.socialeshop.microservices.api.repositories;

import com.socialeshop.microservices.api.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
