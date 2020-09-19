package com.socialeshop.identification.controllers;

import com.socialeshop.identification.payloads.UserRequest;
import net.bytebuddy.pool.TypePool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello")
    public void hello_print_payload(@RequestBody UserRequest input) {
        System.out.println("in hello ");
        System.out.println("username" + input.getUsername());
    }
}
