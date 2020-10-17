package com.socialeshop.api.core.inventory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface InventoryService {
    @GetMapping(
            value    = "/product/{inventoryId}",
            produces = "application/json")
    Inventory getInventory(@PathVariable int inventoryId);
}
