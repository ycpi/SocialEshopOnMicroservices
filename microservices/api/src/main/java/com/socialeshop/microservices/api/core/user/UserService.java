package com.socialeshop.microservices.api.core.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserService {
    @GetMapping(
            value    = "/product/{productId}",
            produces = "application/json")
    UserInfo getUser(@PathVariable int productId);
}
