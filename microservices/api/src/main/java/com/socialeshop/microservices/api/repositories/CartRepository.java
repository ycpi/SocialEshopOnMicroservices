package com.socialeshop.microservices.api.repositories;

import com.socialeshop.microservices.api.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
