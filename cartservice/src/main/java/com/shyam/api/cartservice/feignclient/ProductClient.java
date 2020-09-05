package com.shyam.api.cartservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shyam.commonlib.entity.CustomResponse;

@FeignClient(name = "product-service")
public interface ProductClient {

	@GetMapping(value = "/products/{id}")
	public CustomResponse findById(@PathVariable(value = "id") Long productId);
}
