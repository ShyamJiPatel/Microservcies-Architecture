package com.shyam.api.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shyam.api.userservice.entity.Permission;

@Repository
public interface PermissionDao extends JpaRepository<Permission, Long> {

	@Query("SELECT p FROM Permission p WHERE p.name = :name AND p.archived = false")
	public Permission findByName(@Param("name") String name);;
}