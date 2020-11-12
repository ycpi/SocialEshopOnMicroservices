package com.socialeshop.microservices.api.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "\"Cart\"")
public class Cart {
    // Primary Key: ID, incremented 1 every time
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = true)
    private Inventory inventory;

    @Column(name = "cost", nullable = true)
    private int cost;

    @Column(name = "amount", nullable = true)
    private int amount;

    // the following columns are used for order
    @Column(name = "isOrder", nullable = true)
    private boolean isOrder;

    @Column(name = "comment", nullable = true)
    private String comment;

    @Column(name = "rate", nullable = true)
    private int rate;

    public Cart(User user,Inventory inventory, int amount, int cost) {
        this.id = 0L;
        this.user = user;
        this.inventory = inventory;
        this.cost = cost;
        this.amount = amount;
    }
    public Cart(){
        this.id = 0L;
    }

    public Cart(Cart cart){
        this.id = cart.getId();
        this.user = cart.getUser();
        this.inventory = cart.getInventory();
        this.cost = cart.getCost();
        this.amount = cart.getAmount();
    }

    public Cart(User user, Inventory inventory, int cost, int amount, boolean isOrder, String comment, int rate) {
        this.id = 0L;
        this.user = user;
        this.inventory = inventory;
        this.cost = cost;
        this.amount = amount;
        this.isOrder = isOrder;
        this.comment = comment;
        this.rate = rate;
    }

    public Long getId() {
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

    public boolean isOrder() {
        return isOrder;
    }

    public String getComment() {
        return comment;
    }

    public int getRate() {
        return rate;
    }

    public void setId(Long id) {
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

    public void setOrder(boolean order) {
        isOrder = order;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
