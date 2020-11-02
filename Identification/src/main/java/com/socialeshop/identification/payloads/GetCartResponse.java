package com.socialeshop.identification.payloads;

import com.socialeshop.identification.models.Cart;

import java.util.List;

public class GetCartResponse {
    private List<Cart> cartList;

    public GetCartResponse(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }
}
