package com.shyam.api.userservice.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.api.userservice.constants.AppMessage;
import com.shyam.api.userservice.dao.AddressDao;
import com.shyam.api.userservice.entity.Address;
import com.shyam.api.userservice.entity.UserDetails;
import com.shyam.api.userservice.service.AddressService;
import com.shyam.api.userservice.service.UserService;
import com.shyam.commonlib.exception.AddressNotFoundException;
import com.shyam.commonlib.exception.ResourceNotFoundException;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private UserService userService;

	@Autowired
	private AddressDao addressDao;

	@Override
	@Transactional
	public Address saveUpdate(Address address, Long userId) {
		Optional<UserDetails> user = userService.findById(userId);

		if (user.isPresent()) {
			address.setUser(user.get());
			return addressDao.save(address);
		} else {
			throw new ResourceNotFoundException(AppMessage.USER_NOT_FOUND);
		}
	}

	@Override
	public Address findById(Long userId, Long id) {
		Address address = addressDao.findById(userId, id);

		if (address != null) {
			return address;
		} else {
			throw new AddressNotFoundException(AppMessage.ADDRESS_NOT_FOUND);
		}
	}

	@Override
	public List<Address> findAll(Long userId) {
		return addressDao.findAll(userId);
	}

	@Override
	@Transactional
	public void deleteById(Long userId, Long id) {
		addressDao.deleteById(userId, id);
	}

	@Override
	@Transactional
	public void archiveById(Long userId, Long id) {
		addressDao.archiveById(userId, id);
	}
}
