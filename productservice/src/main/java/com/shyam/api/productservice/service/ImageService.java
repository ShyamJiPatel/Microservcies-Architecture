package com.shyam.api.productservice.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shyam.api.productservice.entity.Image;

public interface ImageService {

	Image saveUpdate(Long userId, MultipartFile file);

	Image findById(Long userId, Long id);

	Image findByFileName(Long userId, String fileName);

	List<Image> findByProductId(Long userId);

	void archiveById(Long userId, Long id);

	void deleteById(Long userId, Long id);

}
