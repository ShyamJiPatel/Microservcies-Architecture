package com.shyam.api.userservice.service;

import java.util.List;

import com.shyam.api.userservice.entity.UserVerificationStatus;

public interface UserVerificationService {

	public UserVerificationStatus saveUpdate(UserVerificationStatus address, Long userId);

	public UserVerificationStatus findById(Long userId, Long id);

	public List<UserVerificationStatus> findAll(Long userId);

	public void deleteById(Long userId, Long id);

	public void archiveById(Long userId, Long id);
}
