package com.shyam.api.userservice.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyam.api.userservice.dao.RoleDao;
import com.shyam.api.userservice.dao.UserDao;
import com.shyam.api.userservice.entity.Role;
import com.shyam.api.userservice.entity.UserDetails;
import com.shyam.api.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleRipository;

	@Override
	@Transactional
	public UserDetails saveUpdate(UserDetails user) {

		Set<Role> roles = user.getRoles();
		if (roles != null && roles.size() > 0) {
			Set<Role> roleList = new HashSet<Role>();
			roles.stream().forEach(role -> {
				roleList.add(roleRipository.findByName(role.getName()));
			});
			user.setRoles(roleList);
		}

		return userDao.save(user);
	}

	@Override
	public Optional<UserDetails> findById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public List<UserDetails> findAll() {
		return userDao.findAll();
	}

	@Override
	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

	@Override
	@Transactional
	public void archiveById(Long id) {
		userDao.archiveById(id);
	}
}
