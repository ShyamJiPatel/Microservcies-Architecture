package com.shyam.api.productservice.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.api.productservice.constants.AppMessage;
import com.shyam.api.productservice.dao.CategoryDao;
import com.shyam.api.productservice.entity.Category;
import com.shyam.api.productservice.service.CategoryService;
import com.shyam.commonlib.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	@Transactional
	public Category saveUpdate(Category user) {
		return categoryDao.save(user);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryDao.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public void deleteById(Long id) {
		categoryDao.deleteById(id);
	}

	@Override
	@Transactional
	public void archiveById(Long id) {
		categoryDao.archiveById(id);
	}

	@Override
	public Category saveUpdateSubcategory(Long parentCategoryId, Category subcategory) {
		Optional<Category> parentCategory = categoryDao.findById(parentCategoryId);

		if (parentCategory.isPresent()) {
			subcategory.setParentCategory(parentCategory.get());
			Category dbSubCategory = categoryDao.save(subcategory);
			log.info(dbSubCategory.toString());
			return dbSubCategory;
		} else {
			throw new ResourceNotFoundException(AppMessage.PARENT_CATEGORY_NOT_FOUND);
		}
	}

	@Override
	public Category findById(Long parentCategoryId, Long id) {
		Optional<Category> parentCategory = categoryDao.findById(parentCategoryId);

		if (parentCategory.isPresent()) {
			return categoryDao.findById(parentCategoryId, id);
		} else {
			throw new ResourceNotFoundException(AppMessage.PARENT_CATEGORY_NOT_FOUND);
		}
	}

	@Override
	public List<Category> findAll(Long parentCategoryId) {
		Optional<Category> parentCategory = categoryDao.findById(parentCategoryId);

		if (parentCategory.isPresent()) {
			return categoryDao.findAll(parentCategoryId);
		} else {
			throw new ResourceNotFoundException(AppMessage.PARENT_CATEGORY_NOT_FOUND);
		}
	}

	@Override
	@Transactional
	public void archiveById(Long parentCategoryId, Long id) {
		categoryDao.archiveById(parentCategoryId, id);
	}

	@Override
	@Transactional
	public void deleteById(Long parentCategoryId, Long id) {
		categoryDao.deleteById(parentCategoryId, id);
	}
}
