package com.shyam.api.userservice.service;

import java.util.List;
import java.util.Optional;

import com.shyam.api.userservice.entity.UserDetails;

public interface UserService {
	public UserDetails saveUpdate(UserDetails user);

	public Optional<UserDetails> findById(Long id);

	public List<UserDetails> findAll();

	public void deleteById(Long id);

	public void archiveById(Long id);
}
