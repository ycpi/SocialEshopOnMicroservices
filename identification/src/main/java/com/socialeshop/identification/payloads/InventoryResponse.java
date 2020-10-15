package com.socialeshop.identification.payloads;

import com.socialeshop.identification.models.Inventory;

import java.util.List;

public class InventoryResponse {
    private List<Inventory> inventoryList;

    public InventoryResponse(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
