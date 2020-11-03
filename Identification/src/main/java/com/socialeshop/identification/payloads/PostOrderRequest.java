package com.socialeshop.identification.payloads;

import com.socialeshop.identification.models.Cart;

import java.util.ArrayList;
import java.util.List;

public class PostOrderRequest {
    private String username;
    private List<Cart> orders = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public List<Cart> getOrderList() {
        return orders;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrderList(List<Cart> orderList) {
        this.orders = orderList;
    }
}
