package com.socialeshop.identification.payloads;

import com.socialeshop.identification.models.Inventory;

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
