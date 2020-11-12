package com.socialeshop.microservices.api.core.payloads.inventory;


import com.socialeshop.microservices.api.core.DTO.SingleInventory;

import java.util.List;

public class InventoryResponse {
    private List<SingleInventory> inventoryList;

    public InventoryResponse(List<SingleInventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public List<SingleInventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<SingleInventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
