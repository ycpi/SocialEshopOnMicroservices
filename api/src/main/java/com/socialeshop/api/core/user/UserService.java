package com.socialeshop.api.core.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserService {
    @GetMapping(
            value    = "/product/{productId}",
            produces = "application/json")
    User getUser(@PathVariable int productId);
}
