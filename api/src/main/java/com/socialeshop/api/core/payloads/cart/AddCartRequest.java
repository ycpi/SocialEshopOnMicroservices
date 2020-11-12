package com.socialeshop.api.core.payloads.cart;

public class AddCartRequest{
    private String username;
    private String item;
    private long itemID;
    private int amount;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public String getItem() {
        return item;
    }

    public long getItemID() {
        return itemID;
    }

    public int getAmount() {
        return amount;
    }
}


