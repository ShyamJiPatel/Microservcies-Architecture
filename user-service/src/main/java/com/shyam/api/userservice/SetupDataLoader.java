package com.shyam.api.userservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.shyam.api.userservice.constants.AppConstants;
import com.shyam.api.userservice.dao.PermissionDao;
import com.shyam.api.userservice.dao.RoleDao;
import com.shyam.api.userservice.entity.Permission;
import com.shyam.api.userservice.entity.Role;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private RoleDao roleRepository;

	@Autowired
	private PermissionDao permissionRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup) {
			return;
		}

		Permission readPermission = createPermissionIfNotExist(AppConstants.READ_PERMISSION);
		Permission writePermission = createPermissionIfNotExist(AppConstants.WRITE_PERMISSION);
		Permission viewPermission = createPermissionIfNotExist(AppConstants.VIEW_PERMISSION);
		Permission editPermission = createPermissionIfNotExist(AppConstants.EDIT_PERMISSION);

		Set<Permission> adminPermissions = new HashSet<Permission>(
				Arrays.asList(readPermission, writePermission, viewPermission, editPermission));
		Set<Permission> userPermissions = new HashSet<Permission>(Arrays.asList(readPermission, viewPermission));

		createRoleIfNotExist(AppConstants.ADMIN_ROLE, adminPermissions);
		createRoleIfNotExist(AppConstants.SELLER_ROLE, adminPermissions);
		createRoleIfNotExist(AppConstants.USER_ROLE, userPermissions);
		createRoleIfNotExist(AppConstants.BUYER_ROLE, userPermissions);

		alreadySetup = true;
	}

	@Transactional
	private Role createRoleIfNotExist(String name, Set<Permission> permissions) {
		Role role = roleRepository.findByName(name);

		if (role != null) {
			return role;
		} else {
			Role newRole = new Role(name);
			newRole.setPermissions(permissions);
			return roleRepository.save(newRole);
		}
	}

	@Transactional
	private Permission createPermissionIfNotExist(String name) {
		Permission permission = permissionRepository.findByName(name);

		if (permission != null) {
			return permission;
		} else {
			Permission newPermission = new Permission(name);
			return permissionRepository.save(newPermission);
		}
	}
}
