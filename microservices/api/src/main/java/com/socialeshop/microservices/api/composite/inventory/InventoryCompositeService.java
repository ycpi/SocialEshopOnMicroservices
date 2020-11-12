package com.socialeshop.microservices.api.composite.inventory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface InventoryCompositeService {
    /**
     * Sample usage: curl $HOST:$PORT/product-composite/1
     *
     * @param inventoryId
     * @return the composite product info, if found, else null
     */

    @GetMapping(
            value    = "/inventory-composite/{inventoryId}",
            produces = "application/json")
    InventoryAggregate getProduct(@PathVariable int inventoryId);
}
