package com.stackroute.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/v1/**")
                        .uri("http://localhost:8084/"))
//                .route(p->p
//                        .path("/api/v2/**")
//                        .uri("http://localhost:9546/"))
                .route(p -> p
                        .path("/api/v3/**")
                        .uri("http://localhost:8888/"))
                .route(p->p
                        .path("/api/v4/**")
                        .uri("http://localhost:9093/"))
                .route(p -> p
                        .path("/api/v5/**")
                        .uri("http://localhost:8083/"))
//                .route(p->p
//                        .path("/oauth2/*","/login/*")
//                        .uri("http://localhost:8088/"))
                .route(p->p
                        .path("/api/v7/**")
                        .uri("http://localhost:9542/"))
                .route(p -> p
                        .path("/api/v8/**")
                        .uri("http://localhost:8081/"))
                .route(p->p
                        .path("/api/v9/**")
                        .uri("http://localhost:9000/"))
                .route(p -> p
                        .path("/api/v11/**")
                        .uri("http://localhost:8087/"))

                .build();
    }

}