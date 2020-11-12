package com.socialeshop.api.core.payloads.inventory;


import com.socialeshop.api.core.DTO.SingleInventory;

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
