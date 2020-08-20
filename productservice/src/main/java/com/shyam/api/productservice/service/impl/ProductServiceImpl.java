package com.shyam.api.productservice.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.api.productservice.dao.ImageDao;
import com.shyam.api.productservice.dao.ProductDao;
import com.shyam.api.productservice.entity.Category;
import com.shyam.api.productservice.entity.Image;
import com.shyam.api.productservice.entity.Product;
import com.shyam.api.productservice.service.CategoryService;
import com.shyam.api.productservice.service.ProductService;
import com.shyam.api.productservice.util.CustomValueGenerator;
import com.shyam.commonlib.exception.ResourceNotFoundException;
import com.shyam.commonlib.util.FileStorageUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ImageDao imageDao;

	@Autowired
	private CategoryService categoryService;

	@Override
	@Transactional
	public Product saveUpdate(Long categoryId, Product product) {

		Optional<Category> category = categoryService.findById(categoryId);

		if (category.isPresent()) {
			product.setCategory(category.get());

			if (product.getCode() == null || "".equals(product.getCode())) {
				product.setCode(generateProductCode());
			}

			return productDao.save(product);
		} else {
			throw new ResourceNotFoundException("Category not found");
		}

	}

	@Override
	public Optional<Product> findById(Long categoryId, Long id) {
		return productDao.findById(categoryId, id);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productDao.findById(id);
	}

	@Override
	public List<Product> findAll(Long categoryId) {
		return productDao.findAll(categoryId);
	}

	@Override
	@Transactional
	public void deleteById(Long categoryId, Long id) {
		productDao.deleteById(categoryId, id);
		deleteImages(id);
	}

	@Override
	@Transactional
	public void archiveById(Long categoryId, Long id) {
		productDao.archiveById(categoryId, id);
	}

	private void deleteImages(Long productId) {
		List<Image> images = imageDao.findByProductId(productId);

		if (images != null && images.size() > 0) {
			for (Image image : images) {
				FileStorageUtil.delete(image.getCreatedName() + image.getExtension());
			}
		}
	}

	public String generateProductCode() {
		String prefix = "PRO";
		return CustomValueGenerator.generateProductCode(prefix);
	}
}
