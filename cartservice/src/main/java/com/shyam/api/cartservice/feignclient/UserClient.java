package com.shyam.api.cartservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shyam.api.cartservice.entity.UserDetails;

@FeignClient(name = "user-service")
public interface UserClient {

	@GetMapping(value = "/users/{id}")
	public UserDetails findById(@PathVariable(value = "id") Long userId);
}
