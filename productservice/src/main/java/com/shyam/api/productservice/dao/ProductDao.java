package com.shyam.api.productservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shyam.api.productservice.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.id = :id AND p.archived = false")
	public Optional<Product> findById(@Param("categoryId") Long categoryId, @Param("id") Long id);

	@Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.archived = false")
	public List<Product> findAll(@Param("categoryId") Long categoryId);

	@Query("UPDATE Product p SET p.archived = true WHERE p.category.id = :categoryId AND p.id = :id")
	@Modifying
	public void archiveById(@Param("categoryId") Long categoryId, Long id);

	@Modifying
	@Query("DELETE FROM Product p WHERE p.category.id = :categoryId AND p.id = :id")
	public void deleteById(@Param("categoryId") Long categoryId, @Param("id") Long id);
}