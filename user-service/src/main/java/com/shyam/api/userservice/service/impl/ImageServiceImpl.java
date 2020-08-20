package com.shyam.api.userservice.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shyam.api.userservice.dao.ImageDao;
import com.shyam.api.userservice.entity.Image;
import com.shyam.api.userservice.entity.UserDetails;
import com.shyam.api.userservice.service.ImageService;
import com.shyam.api.userservice.service.UserService;
import com.shyam.commonlib.exception.ResourceNotFoundException;
import com.shyam.commonlib.util.ConversionUtil;
import com.shyam.commonlib.util.FileStorageUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDao imageDao;

	@Autowired
	private UserService userService;

	@Override
	public Image saveUpdate(Long userId, MultipartFile file) {

		Image existingImage = findByUserId(userId);

		try {

			String originalName = file.getOriginalFilename();

			if (file == null || "".equals(originalName)) {
				throw new RuntimeException("Input File not found!");
			}

			String generatedName = ConversionUtil.getAlphaNumeric(System.currentTimeMillis()).toUpperCase();
			String modifiedName = generatedName.substring(2).toUpperCase();
			String extension = FileStorageUtil.getExtension(originalName);

			if (existingImage != null) {

				FileStorageUtil.delete(existingImage.getCreatedName() + extension);

				existingImage.setOriginalName(originalName);
				existingImage.setCreatedName(modifiedName);
				existingImage.setExtension(extension);
				existingImage.setType(file.getContentType());

			} else {
				existingImage = new Image(modifiedName, originalName, extension, file.getContentType());

				Optional<UserDetails> user = userService.findById(userId);
				if (user.isPresent()) {
					existingImage.setUser(user.get());
				} else {
					throw new ResourceNotFoundException("User not found");
				}
			}

			boolean fileSaved = FileStorageUtil.save(file, modifiedName + extension);

			if (!fileSaved) {
				log.info("Could not save the file " + file.getOriginalFilename() + "!");
				throw new RuntimeException("Could not save the file " + file.getOriginalFilename() + "!");
			}

			Image dbImage = imageDao.save(existingImage);
			Resource savedFile = FileStorageUtil.load(modifiedName + extension);

			dbImage.setUrl(savedFile.getURI().toString());

			return dbImage;

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Image findById(Long userId, Long id) {
		try {
			Image dbImage = imageDao.findById(userId, id);
			Resource savedFile = FileStorageUtil.load(dbImage.getCreatedName() + dbImage.getExtension());
			dbImage.setUrl(savedFile.getURI().toString());
			return dbImage;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Image findByFileName(Long userId, String fileName) {
		try {
			Image dbImage = imageDao.findByFileName(userId, fileName);
			Resource savedFile = FileStorageUtil.load(dbImage.getCreatedName() + dbImage.getExtension());
			dbImage.setUrl(savedFile.getURI().toString());
			return dbImage;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Image findByUserId(Long userId) {
		try {
			Image dbImage = imageDao.findByUserId(userId);
			Resource savedFile = FileStorageUtil.load(dbImage.getCreatedName() + dbImage.getExtension());
			dbImage.setUrl(savedFile.getURI().toString());
			return dbImage;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void archiveById(Long userId, Long id) {
		imageDao.archiveById(userId, id);
	}

	@Override
	@Transactional
	public void deleteById(Long userId, Long id) {
		Image dbImage = imageDao.findByUserId(userId);
		imageDao.deleteById(userId, id);
		FileStorageUtil.delete(dbImage.getCreatedName() + dbImage.getExtension());
	}
}
