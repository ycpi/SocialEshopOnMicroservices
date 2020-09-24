package com.socialeshop.identification.controllers;

import com.socialeshop.identification.Security.jwt.JwtUtils;
import com.socialeshop.identification.payloads.UserRequest;
import com.sun.istack.NotNull;
import net.bytebuddy.pool.TypePool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    private static final Logger logger = LoggerFactory.getLogger(helloController.class);

    @GetMapping("/hello")
    public String hello() {
        System.out.println("in hello");
        return "hello";
    }

    @PostMapping("/hello")
    public void hello_print_payload(@NotNull @RequestBody UserRequest input) {
//        logger.info("in hello ");
//        logger.info("print username:");
        logger.info("print username:" + input.getUsername());
//        System.out.println("in hello");
//        System.out.println("username" + input.getUsername());
    }
}
