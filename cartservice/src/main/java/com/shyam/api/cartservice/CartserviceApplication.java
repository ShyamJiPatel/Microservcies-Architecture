package com.shyam.api.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@EnableJpaRepositories
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = { "com.shyam.api.cartservice", "com.shyam.commonlib" })
public class CartserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartserviceApplication.class, args);
	}

}
