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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
