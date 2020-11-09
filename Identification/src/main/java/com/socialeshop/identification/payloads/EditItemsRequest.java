package com.socialeshop.identification.payloads;

public class EditItemsRequest {
    private int newAmount;
    private long id;

    public void setNewAmount(int newAmount) {
        this.newAmount = newAmount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNewAmount() {
        return newAmount;
    }

    public long getId() {
        return id;
    }
}
