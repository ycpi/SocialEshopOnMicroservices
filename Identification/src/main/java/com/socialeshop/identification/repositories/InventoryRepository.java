package com.socialeshop.identification.repositories;

import com.socialeshop.identification.models.Inventory;
import com.socialeshop.identification.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
