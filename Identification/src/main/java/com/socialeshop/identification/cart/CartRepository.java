package com.socialeshop.identification.repositories;

import com.socialeshop.identification.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
