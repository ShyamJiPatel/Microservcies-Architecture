package com.shyam.api.userservice.service;

import org.springframework.web.multipart.MultipartFile;

import com.shyam.api.userservice.entity.Image;

public interface ImageService {

	Image saveUpdate(Long userId, MultipartFile file);

	Image findById(Long userId, Long id);
	
	Image findByFileName(Long userId, String fileName);

	Image findByUserId(Long userId);

	void archiveById(Long userId, Long id);

	void deleteById(Long userId, Long id);
	
}
