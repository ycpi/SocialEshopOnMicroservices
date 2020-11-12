package com.socialeshop.microservices.api.core.payloads.order;

import com.socialeshop.microservices.api.core.DTO.SingleCart;

import java.util.List;

public class GetCartResponse {
    private List<SingleCart> cartList;

    public GetCartResponse(List<SingleCart> cartList) {
        this.cartList = cartList;
    }

    public List<SingleCart> getCartList() {
        return cartList;
    }

    public void setCartList(List<SingleCart> cartList) {
        this.cartList = cartList;
    }
}
