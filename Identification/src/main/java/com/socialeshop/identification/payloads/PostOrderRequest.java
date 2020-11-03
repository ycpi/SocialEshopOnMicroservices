package com.socialeshop.identification.payloads;

import com.socialeshop.identification.models.Cart;

import java.util.List;

public class PostOrderRequest {
    private String username;
    private List<Cart> orderList;

    public String getUsername() {
        return username;
    }

    public List<Cart> getOrderList() {
        return orderList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrderList(List<Cart> orderList) {
        this.orderList = orderList;
    }
}
