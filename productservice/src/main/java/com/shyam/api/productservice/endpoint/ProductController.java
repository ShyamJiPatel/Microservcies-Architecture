package com.shyam.api.productservice.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.api.productservice.constants.AppMessage;
import com.shyam.api.productservice.entity.Product;
import com.shyam.api.productservice.service.ProductService;
import com.shyam.commonlib.entity.CustomResponse;
import com.shyam.commonlib.exception.ResourceNotFoundException;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping(value = "/categories/{categoryId}/products", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> saveUpdate(@PathVariable Long categoryId,
			@Valid @RequestBody Product product) {

		Product dbProduct = productService.saveUpdate(categoryId, product);

		return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse(dbProduct));
	}

	@GetMapping(value = "/categories/{categoryId}/products/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findById(@PathVariable Long categoryId, @PathVariable Long id) {

		Optional<Product> product = productService.findById(categoryId, id);

		if (product.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(product.get()));
		} else {
			throw new ResourceNotFoundException(AppMessage.PRODUCT_NOT_FOUND);
		}
	}

	@GetMapping(value = "/categories/{categoryId}/products", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findAll(@PathVariable Long categoryId) {

		List<Product> allProduct = productService.findAll(categoryId);

		if (allProduct != null && allProduct.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(allProduct));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(new ArrayList<Product>()));
		}
	}

	@PostMapping(value = "/categories/{categoryId}/products/archive/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> archiveById(@PathVariable Long categoryId, @PathVariable Long id) {

		productService.archiveById(categoryId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}

	@DeleteMapping(value = "/categories/{categoryId}/products/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> deleteById(@PathVariable Long categoryId, @PathVariable Long id) {

		productService.deleteById(categoryId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}

	@GetMapping(value = "/products/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findProductById(@PathVariable Long id) {

		Optional<Product> product = productService.findById(id);

		if (product.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(product.get()));
		} else {
			throw new ResourceNotFoundException(AppMessage.PRODUCT_NOT_FOUND);
		}
	}
}
