package com.shyam.api.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shyam.api.userservice.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

	@Query("SELECT r FROM Role r WHERE r.name = :name AND r.archived = false")
	public Role findByName(@Param("name") String name);;
}