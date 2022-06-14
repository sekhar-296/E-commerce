package com.stackroute.productownerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductOwnerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductOwnerServiceApplication.class, args);
	}

}