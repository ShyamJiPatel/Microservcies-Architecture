package com.shyam.api.productservice.service;

import java.util.List;
import java.util.Optional;

import com.shyam.api.productservice.entity.Product;

public interface ProductService {

	public Product saveUpdate(Long categoryId, Product product);

	public Optional<Product> findById(Long categoryId, Long id);

	public List<Product> findAll(Long categoryId);

	public void deleteById(Long categoryId, Long id);

	public void archiveById(Long categoryId, Long id);

	public Optional<Product> findById(Long id);
}
