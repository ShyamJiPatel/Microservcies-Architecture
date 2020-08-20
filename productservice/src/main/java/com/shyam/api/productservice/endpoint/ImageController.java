package com.shyam.api.productservice.endpoint;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shyam.api.productservice.constants.AppMessage;
import com.shyam.api.productservice.entity.Image;
import com.shyam.api.productservice.service.ImageService;
import com.shyam.commonlib.entity.CustomResponse;
import com.shyam.commonlib.exception.ResourceNotFoundException;

@RestController
public class ImageController {

	@Autowired
	private ImageService imageService;

	@PostMapping(value = "/products/{productId}/images", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> saveUpdate(@PathVariable Long productId,
			@RequestParam("file") MultipartFile file) {

		Image dbImage = imageService.saveUpdate(productId, file);

		return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse(dbImage));
	}

	@GetMapping(value = "/products/{productId}/images/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findById(@PathVariable Long productId, @PathVariable Long id) {

		Image dbImage = imageService.findById(productId, id);

		if (dbImage != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(dbImage));
		} else {
			throw new ResourceNotFoundException(AppMessage.IMAGE_NOT_FOUND);
		}
	}

	@GetMapping(value = "/products/{productId}/images", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findByProductId(@PathVariable Long productId) {

		List<Image> images = imageService.findByProductId(productId);

		if (images != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(images));
		} else {
			throw new ResourceNotFoundException(AppMessage.IMAGE_NOT_FOUND);
		}
	}

	@PostMapping(value = "/products/{productId}/images/archive/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> archiveById(@PathVariable Long productId, @PathVariable Long id) {

		imageService.archiveById(productId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}

	@DeleteMapping(value = "/products/{productId}/images/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> deleteById(@PathVariable Long productId, @PathVariable Long id) {

		imageService.deleteById(productId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}
}
