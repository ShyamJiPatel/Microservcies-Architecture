package com.shyam.api.productservice.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.api.productservice.dao.ProductDao;
import com.shyam.api.productservice.entity.Product;
import com.shyam.api.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	@Transactional
	public Product saveUpdate(Product user) {
		return productDao.save(user);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productDao.findById(id);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public void deleteById(Long id) {
		productDao.deleteById(id);
	}

	@Override
	@Transactional
	public void archiveById(Long id) {
		productDao.archiveById(id);
	}
}
