package com.shyam.api.cartservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shyam.commonlib.entity.CustomResponse;

@FeignClient(name = "user-service")
public interface UserClient {

	@GetMapping(value = "/users/{id}")
	public CustomResponse findById(@PathVariable(value = "id") Long userId);
}
