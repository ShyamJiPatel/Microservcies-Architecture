package com.shyam.api.userservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shyam.api.userservice.entity.UserVerificationStatus;

@Repository
public interface UserVerificationDao extends JpaRepository<UserVerificationStatus, Long> {

	@Query("SELECT a FROM UserVerificationStatus a WHERE a.user.id = :userId AND a.id = :id AND a.archived = false")
	public UserVerificationStatus findById(@Param("userId") Long userId, @Param("id") Long id);

	@Query("SELECT a FROM UserVerificationStatus a WHERE a.user.id = :userId AND a.archived = false")
	public List<UserVerificationStatus> findAll(@Param("userId") Long userId);

	@Modifying
	@Query("DELETE FROM UserVerificationStatus a WHERE a.user.id = :userId AND a.id = :id")
	public void deleteById(@Param("userId") Long userId, @Param("id") Long id);

	@Modifying
	@Query("UPDATE UserVerificationStatus a SET a.archived = true WHERE a.user.id = :userId AND a.id = :id")
	public void archiveById(@Param("userId") Long userId, @Param("id") Long id);

}
