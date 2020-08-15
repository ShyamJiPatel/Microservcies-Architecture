package com.shyam.api.userservice.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.api.userservice.dao.UserVerificationDao;
import com.shyam.api.userservice.entity.UserDetails;
import com.shyam.api.userservice.entity.UserVerificationStatus;
import com.shyam.api.userservice.service.UserService;
import com.shyam.api.userservice.service.UserVerificationService;
import com.shyam.commonlib.exception.ResourceNotFoundException;
import com.shyam.commonlib.exception.UserNotFoundException;

@Service
public class UserVerificationServiceImpl implements UserVerificationService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserVerificationDao userVerificationDao;

	@Override
	@Transactional
	public UserVerificationStatus saveUpdate(UserVerificationStatus userVerificationStatus, Long userId) {
		Optional<UserDetails> user = userService.findById(userId);

		if (user.isPresent()) {
			userVerificationStatus.setUser(user.get());
		} else {
			throw new UserNotFoundException("User not found");
		}
		return userVerificationDao.save(userVerificationStatus);
	}

	@Override
	public UserVerificationStatus findById(Long userId, Long id) {
		UserVerificationStatus userVerificationStatus = userVerificationDao.findById(userId, id);

		if (userVerificationStatus != null) {
			return userVerificationStatus;
		} else {
			throw new ResourceNotFoundException("UserVerificationStatus not found");
		}
	}

	@Override
	public List<UserVerificationStatus> findAll(Long userId) {
		return userVerificationDao.findAll(userId);
	}

	@Override
	@Transactional
	public void deleteById(Long userId, Long id) {
		userVerificationDao.deleteById(userId, id);
	}

	@Override
	@Transactional
	public void archiveById(Long userId, Long id) {
		userVerificationDao.archiveById(userId, id);
	}
}
