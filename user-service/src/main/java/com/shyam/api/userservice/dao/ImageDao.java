package com.shyam.api.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shyam.api.userservice.entity.Image;

@Repository
public interface ImageDao extends JpaRepository<Image, Long> {

	@Query("SELECT i FROM Image i WHERE i.user.id = :userId AND i.id = :id AND i.archived = false")
	public Image findById(@Param("userId") Long userId, @Param("id") Long id);

	@Query("SELECT i FROM Image i WHERE i.user.id = :userId AND i.archived = false")
	public Image findByUserId(@Param("userId") Long userId);

	@Modifying
	@Query("DELETE FROM Image i WHERE i.user.id = :userId AND i.id = :id")
	public void deleteById(@Param("userId") Long userId, @Param("id") Long id);

	@Modifying
	@Query("UPDATE Image i SET i.archived = true WHERE i.user.id = :userId AND i.id = :id")
	public void archiveById(@Param("userId") Long userId, @Param("id") Long id);

	@Query("SELECT i FROM Image i WHERE i.user.id = :userId AND i.createdName = :fileName AND i.archived = false")
	public Image findByFileName(@Param("userId") Long userId, @Param("fileName") String fileName);

}
