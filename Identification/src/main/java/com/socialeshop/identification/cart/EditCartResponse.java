package com.socialeshop.identification.payloads;

public class EditCartResponse {
    private int cost;
    private int amount;

    public EditCartResponse(int cost, int amount) {
        this.cost = cost;
        this.amount = amount;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCost() {
        return cost;
    }

    public int getAmount() {
        return amount;
    }
}
