package com.shyam.api.userservice.service;

import java.util.List;

import com.shyam.api.userservice.entity.Address;

public interface AddressService {
	public Address saveUpdate(Address address, Long userId);

	public Address findById(Long userId, Long id);

	public List<Address> findAll(Long userId);

	public void deleteById(Long userId, Long id);
	
	public void archiveById(Long userId, Long id);
}
