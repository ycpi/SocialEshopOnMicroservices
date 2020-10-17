package com.socialeshop.identification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.socialeshop")
public class IdentificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentificationApplication.class, args);
    }

}
