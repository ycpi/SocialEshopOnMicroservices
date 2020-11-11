package com.socialeshop.identification.payloads;
// return price: response.data.cost, item: response.data.item, num: response.data.amount, id: response.data.id,
public class AddCartResponse {
    private int cost;
    private String item;
    private int amount;
    private long id;

    public AddCartResponse(int cost, String item, int amount, long id) {
        this.cost = cost;
        this.item = item;
        this.amount = amount;
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public String getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public long getId() {
        return id;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setItemName(String item) {
        this.item = item;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setId(long id) {
        this.id = id;
    }
}
