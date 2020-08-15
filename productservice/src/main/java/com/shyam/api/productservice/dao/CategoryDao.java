package com.shyam.api.productservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shyam.api.productservice.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

	@Query("SELECT c FROM Category c WHERE c.id = :id AND c.archived = false")
	public Optional<Category> findById(@Param("id") Long id);

	@Query("SELECT c FROM Category c WHERE c.archived = false")
	public List<Category> findAll();

	@Query("UPDATE Category c SET c.archived = true WHERE c.id = :id")
	@Modifying
	public void archiveById(Long id);

	@Query("SELECT c FROM Category c WHERE c.parentCategory.id = :parentCategoryId AND c.id = :id AND c.archived = false")
	public Category findById(@Param("parentCategoryId") Long parentCategoryId, @Param("id") Long id);

	@Query("SELECT c FROM Category c WHERE c.parentCategory.id = :parentCategoryId AND c.archived = false")
	public List<Category> findAll(@Param("parentCategoryId") Long parentCategoryId);

	@Modifying
	@Query("DELETE FROM Category c WHERE c.parentCategory.id = :parentCategoryId AND c.id = :id")
	public void deleteById(@Param("parentCategoryId") Long parentCategoryId, @Param("id") Long id);

	@Modifying
	@Query("UPDATE Category c SET c.archived = true WHERE c.parentCategory.id = :parentCategoryId AND c.id = :id")
	public void archiveById(@Param("parentCategoryId") Long parentCategoryId, @Param("id") Long id);
}