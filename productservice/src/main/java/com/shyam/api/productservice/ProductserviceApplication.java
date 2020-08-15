package com.shyam.api.productservice;

import java.time.LocalDateTime;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;

@EnableEurekaClient
@EnableJpaRepositories
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = { "com.shyam.api.productservice", "com.shyam.commonlib" })
public class ProductserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		log.error("Spring boot application running in UTC timezone, current timestamp is {} ", LocalDateTime.now());
	}

}
