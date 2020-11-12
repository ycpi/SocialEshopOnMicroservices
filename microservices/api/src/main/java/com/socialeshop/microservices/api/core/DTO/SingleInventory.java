package com.socialeshop.microservices.api.core.DTO;


import com.socialeshop.microservices.api.model.Inventory;

public class SingleInventory {
    private Long id;
    
    private String userName;
    
    private String itemName;

    private int cost;

    private int amount;

    private String description;

    private String tag;

    public SingleInventory(Inventory inventory) {
        this.id = inventory.getId();
        this.userName = inventory.getUser().getUsername();
        this.itemName = inventory.getItemName();
        this.cost = inventory.getCost();
        this.amount = inventory.getAmount();
        this.description = inventory.getDescription();
        this.tag = inventory.getTag();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public  String getTag(){return  tag; }

    public void setTag(String tag) {this.tag = tag;}
}
