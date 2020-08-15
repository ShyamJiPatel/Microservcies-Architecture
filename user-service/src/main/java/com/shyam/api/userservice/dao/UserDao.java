package com.shyam.api.userservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shyam.api.userservice.entity.UserDetails;

@Repository
public interface UserDao extends JpaRepository<UserDetails, Long> {

	@Query("SELECT u FROM UserDetails u WHERE u.id = :id AND u.archived = false")
	public Optional<UserDetails> findById(@Param("id") Long id);

	@Query("SELECT u FROM UserDetails u WHERE u.archived = false")
	public List<UserDetails> findAll();

	@Query("UPDATE UserDetails u SET u.archived = true WHERE u.id = :id")
	@Modifying
	public void archiveById(Long id);
}