package com.socialeshop.api.core.inventory;

import com.socialeshop.api.core.user.UserInfo;

public class Inventory {

    private Long id;

    private String itemName;

    private int cost;

    private int amount;

    private String description;

    private String tag;

    private UserInfo userInfo;

    public Inventory() {
        this.id = 0L;
        this.itemName = "test";
    }

    public Inventory(Long id, String itemName, int cost, int amount, String description) {
        this.id = id;
        this.itemName = itemName;
        this.cost = cost;
        this.amount = amount;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
