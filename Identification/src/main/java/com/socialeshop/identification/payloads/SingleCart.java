package com.socialeshop.identification.payloads;
import com.socialeshop.identification.models.Cart;
import com.socialeshop.identification.models.Inventory;
import com.socialeshop.identification.models.User;

public class SingleCart {
    private long id;
    private String username;
    private String inventoryname;
    private int cost;
    private int amount;

    public SingleCart(long id, String username, String inventoryname, int cost, int amount) {
        this.id = id;
        this.username = username;
        this.inventoryname = inventoryname;
        this.cost = cost;
        this.amount = amount;
    }

    public SingleCart(Cart cart){
        this.id = cart.getId();
        this.username = cart.getUser().getUsername();
        this.inventoryname = cart.getInventory().getItemName();
        this.cost = cart.getCost();
        this.amount = cart.getAmount();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getInventoryname() {
        return inventoryname;
    }

    public int getCost() {
        return cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setInventoryname(String inventoryname) {
        this.inventoryname = inventoryname;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}