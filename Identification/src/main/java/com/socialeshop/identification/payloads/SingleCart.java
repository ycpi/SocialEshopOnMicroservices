package com.socialeshop.identification.payloads;
import com.socialeshop.identification.models.Cart;
import com.socialeshop.identification.models.Inventory;
import com.socialeshop.identification.models.User;

public class SingleCart {
    private long id;
    private User user;
    private Inventory inventory;
    private int cost;
    private int amount;

    public SingleCart(long id, User user, Inventory inventory, int cost, int amount) {
        this.id = id;
        this.user = user;
        this.inventory = inventory;
        this.cost = cost;
        this.amount = amount;
    }

    public SingleCart(Cart cart){
        this.id = cart.getId();
        this.user = cart.getUser();
        this.inventory = cart.getInventory();
        this.cost = cart.getCost();
        this.amount = cart.getAmount();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getCost() {
        return cost;
    }

    public int getAmount() {
        return amount;
    }
}
