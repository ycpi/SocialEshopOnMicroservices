package com.socialeshop.identification.payloads;

public class AddCartRequest{
    private String userName;
    private String itemName;
    private long itemId;
    private int amount;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUserName(){return  userName;}

    public void setUserName(String userName) {this.userName = userName; }

    public long getItemId(){return itemId;}

    public void setItemId(long itemId){this.itemId = itemId;}
}


