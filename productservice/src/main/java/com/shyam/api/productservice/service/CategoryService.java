package com.shyam.api.productservice.service;

import java.util.List;
import java.util.Optional;

import com.shyam.api.productservice.entity.Category;

public interface CategoryService {
	public Category saveUpdate(Category user);

	public Optional<Category> findById(Long id);

	public List<Category> findAll();

	public void deleteById(Long id);

	public void archiveById(Long id);

	public Category saveUpdateSubcategory(Long parentCategoryId, Category subcategory);

	public Category findById(Long parentCategoryId, Long id);

	public List<Category> findAll(Long parentCategoryId);

	public void archiveById(Long categoryId, Long id);

	public void deleteById(Long categoryId, Long id);
}
