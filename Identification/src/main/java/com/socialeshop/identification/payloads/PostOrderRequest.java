package com.socialeshop.identification.payloads;

import com.socialeshop.identification.models.Cart;

import java.util.ArrayList;
import java.util.List;

public class PostOrderRequest {
    private String username;
    private List<Long> orders = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public List<Long> getOrderList() {
        return orders;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrderList(List<Long> orderList) {
        this.orders = orderList;
    }
}
