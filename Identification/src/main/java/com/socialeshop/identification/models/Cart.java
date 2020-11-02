package com.socialeshop.identification.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

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

    public Cart(User user,Inventory inventory, int amount, int cost) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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






}
