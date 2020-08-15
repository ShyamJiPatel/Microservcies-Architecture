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

	@Query("SELECT p FROM Product p WHERE p.id = :id AND p.archived = false")
	public Optional<Product> findById(@Param("id") Long id);

	@Query("SELECT p FROM Product p WHERE p.archived = false")
	public List<Product> findAll();

	@Query("UPDATE Product p SET p.archived = true WHERE p.id = :id")
	@Modifying
	public void archiveById(Long id);
}