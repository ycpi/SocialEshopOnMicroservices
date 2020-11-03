package com.socialeshop.identification.payloads;

import java.util.List;

public class PostOrderResponse {
    List<Long> checkoutStatus;

    public PostOrderResponse(List<Long> checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public List<Long> getCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(List<Long> checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }
}
