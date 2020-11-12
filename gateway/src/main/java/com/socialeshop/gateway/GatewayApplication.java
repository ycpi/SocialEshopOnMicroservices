package com.socialeshop.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        String authServer = "http://localhost:9090";
        String cartServer = "http://localhost:9091";
        String categoryServer = "http://localhost:9092";
        String orderServer = "http://localhost:9093";
//        String authServer = "http://10.56.13.17:9090";
//        String cartServer = "http://10.56.13.17:9090";
//        String categoryServer = "http://10.56.13.17:9090";
//        String orderServer = "http://10.56.13.17:9090";
        return builder.routes()
                .route(p -> p
                        .path("/auth/**")
                        .uri(authServer))
                .route(p -> p
                        .path("/cart/**")
                        .uri(cartServer))
                .route(p -> p
                        .path("/category/**")
                        .uri(categoryServer))
                .route(p -> p
                        .path("/order/**")
                        .uri(orderServer))
                .build();
    }
}
