package com.socialeshop.identification.payloads;

import com.socialeshop.identification.models.Cart;

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
