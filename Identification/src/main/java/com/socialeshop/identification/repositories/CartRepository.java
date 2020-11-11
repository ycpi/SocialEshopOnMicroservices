package com.socialeshop.identification.repositories;

import com.socialeshop.identification.models.Cart;
import com.socialeshop.identification.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
