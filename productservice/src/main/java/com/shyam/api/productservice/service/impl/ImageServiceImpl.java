package com.shyam.api.productservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shyam.api.productservice.constants.AppMessage;
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
		try {
			String originalName = file.getOriginalFilename();

			if (file == null || "".equals(originalName)) {
				throw new RuntimeException("Input File not found!");
			}

			String generatedName = ConversionUtil.getAlphaNumeric(System.currentTimeMillis()).toUpperCase();
			String modifiedName = generatedName.substring(2).toUpperCase();
			String extension = FileStorageUtil.getExtension(originalName);

			Image image = new Image(modifiedName, originalName, extension, file.getContentType());

			Optional<Product> product = productService.findById(productId);
			if (product.isPresent()) {
				image.setProduct(product.get());
			} else {
				throw new ResourceNotFoundException("Product not found");
			}

			boolean fileSaved = FileStorageUtil.save(file, modifiedName + extension);

			if (!fileSaved) {
				log.info("Could not save the file " + file.getOriginalFilename() + "!");
				throw new RuntimeException("Could not save the file " + file.getOriginalFilename() + "!");
			}

			Image dbImage = imageDao.save(image);
			Resource savedFile = FileStorageUtil.load(modifiedName + extension);

			dbImage.setUrl(savedFile.getURI().toString());

			return dbImage;

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Image findById(Long productId, Long id) {
		try {
			Image dbImage = imageDao.findById(productId, id);

			if (dbImage == null) {
				throw new ResourceNotFoundException(AppMessage.IMAGE_NOT_FOUND);
			}
			Resource savedFile = FileStorageUtil.load(dbImage.getCreatedName() + dbImage.getExtension());
			dbImage.setUrl(savedFile.getURI().toString());
			return dbImage;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Image findByFileName(Long productId, String fileName) {
		try {
			Image dbImage = imageDao.findByFileName(productId, fileName);

			if (dbImage == null) {
				throw new ResourceNotFoundException(AppMessage.IMAGE_NOT_FOUND);
			}

			Resource savedFile = FileStorageUtil.load(dbImage.getCreatedName() + dbImage.getExtension());
			dbImage.setUrl(savedFile.getURI().toString());
			return dbImage;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Image> findByProductId(Long productId) {
		try {
			List<Image> dbImages = imageDao.findByProductId(productId);

			List<Image> allImages = new ArrayList<Image>();

			if (dbImages != null && dbImages.size() > 0) {
				for (Image image : dbImages) {
					Resource savedFile = FileStorageUtil.load(image.getCreatedName() + image.getExtension());
					image.setUrl(savedFile.getURI().toString());
					allImages.add(image);
				}
			}
			return allImages;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void archiveById(Long productId, Long id) {
		imageDao.archiveById(productId, id);
	}

	@Override
	@Transactional
	public void deleteById(Long productId, Long id) {
		List<Image> images = imageDao.findByProductId(productId);
		imageDao.deleteById(productId, id);

		if (images != null & images.size() > 0) {
			for (Image image : images) {
				FileStorageUtil.delete(image.getCreatedName() + image.getExtension());
			}
		}
	}
}
