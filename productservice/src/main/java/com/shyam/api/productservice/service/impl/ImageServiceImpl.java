package com.shyam.api.productservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shyam.api.productservice.dao.ImageDao;
import com.shyam.api.productservice.entity.Image;
import com.shyam.api.productservice.entity.Product;
import com.shyam.api.productservice.service.ImageService;
import com.shyam.api.productservice.service.ProductService;
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
	private ProductService productService;

	@Override
	public Image saveUpdate(Long productId, MultipartFile file) {

		Image existingImage = findByProductId(productId);

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

				Optional<Product> product = productService.findById(productId);
				if (product.isPresent()) {
					existingImage.setProduct(product.get());
				} else {
					throw new ResourceNotFoundException("Product not found");
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
		return imageDao.findById(userId, id);
	}

	@Override
	public Image findByFileName(Long userId, String fileName) {
		return imageDao.findByFileName(userId, fileName);
	}

	@Override
	public Image findByProductId(Long userId) {
		return imageDao.findByProductId(userId);
	}

	@Override
	public void archiveById(Long userId, Long id) {
		imageDao.archiveById(userId, id);
	}

	@Override
	public void deleteById(Long userId, Long id) {
		imageDao.deleteById(userId, id);
	}
}
