package com.socialeshop.api.core.inventory;

import org.springframework.web.bind.annotation.*;

public interface InventoryService {
    @GetMapping(
            value    = "/inventory/{inventoryId}",
            produces = "application/json")
    Inventory getInventory(@PathVariable int inventoryId);

    @DeleteMapping(value = "/dele/{productId}")
    void deleteProduct(@PathVariable int productId);
}
