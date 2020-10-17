package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        String authServer = "http://localhost:9090";
        String cartServer = "http://localhost:9090";
        String categoryServer = "http://localhost:9090";
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
                .build();
    }
}