package com.shyam.api.productservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shyam.api.productservice.entity.Image;

@Repository
public interface ImageDao extends JpaRepository<Image, Long> {

	@Query("SELECT i FROM Image i WHERE i.product.id = :productId AND i.id = :id AND i.archived = false")
	public Image findById(@Param("productId") Long productId, @Param("id") Long id);

	@Query("SELECT i FROM Image i WHERE i.product.id = :productId AND i.archived = false")
	public Image findByProductId(@Param("productId") Long productId);

	@Modifying
	@Query("DELETE FROM Image i WHERE i.product.id = :productId AND i.id = :id")
	public void deleteById(@Param("productId") Long productId, @Param("id") Long id);

	@Modifying
	@Query("UPDATE Image i SET i.archived = true WHERE i.product.id = :productId AND i.id = :id")
	public void archiveById(@Param("productId") Long productId, @Param("id") Long id);

	@Query("SELECT i FROM Image i WHERE i.product.id = :productId AND i.createdName = :fileName AND i.archived = false")
	public Image findByFileName(@Param("productId") Long productId, @Param("fileName") String fileName);

}
