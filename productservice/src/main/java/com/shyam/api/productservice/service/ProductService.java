package com.shyam.api.productservice.service;

import java.util.List;
import java.util.Optional;

import com.shyam.api.productservice.entity.Product;

public interface ProductService {
	public Product saveUpdate(Product user);

	public Optional<Product> findById(Long id);

	public List<Product> findAll();

	public void deleteById(Long id);

	public void archiveById(Long id);
}
