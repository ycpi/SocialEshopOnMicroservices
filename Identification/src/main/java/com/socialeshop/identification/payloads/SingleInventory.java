package com.socialeshop.identification.payloads;

import com.socialeshop.identification.models.Inventory;
import com.socialeshop.identification.models.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class SingleInventory {
    private Long id;
    
    private User user;
    
    private String itemName;

    private int cost;

    private int amount;

    private String description;

    public SingleInventory(Inventory inventory) {
        this.id = inventory.getId();
        this.user = inventory.getUser();
        this.itemName = inventory.getItemName();
        this.cost = inventory.getCost();
        this.amount = inventory.getAmount();
        this.description = inventory.getDescription();
    }
}
