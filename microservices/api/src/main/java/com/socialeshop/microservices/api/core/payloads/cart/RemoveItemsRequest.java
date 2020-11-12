package com.socialeshop.microservices.api.core.payloads.cart;

import com.socialeshop.microservices.api.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class RemoveItemsRequest {
    List<Cart> orders = new ArrayList<>();

    public List<Cart> getOrders() {
        return orders;
    }

    public void setOrders(List<Cart> orders) {
        this.orders = orders;
    }
}
