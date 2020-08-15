package com.shyam.api.productservice.endpoint;

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

	@PostMapping(value = "/products/{userId}/images", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> saveUpdate(@PathVariable Long userId,
			@RequestParam("file") MultipartFile file) {

		Image dbImage = imageService.saveUpdate(userId, file);

		return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse(dbImage));
	}

	@GetMapping(value = "/products/{userId}/images/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findById(@PathVariable Long userId, @PathVariable Long id) {

		Image dbImage = imageService.findById(userId, id);

		if (dbImage != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(dbImage));
		} else {
			throw new ResourceNotFoundException(AppMessage.IMAGE_NOT_FOUND);
		}
	}

	@GetMapping(value = "/products/{userId}/images", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findByUserId(@PathVariable Long userId) {

		Image image = imageService.findByProductId(userId);

		if (image != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(image));
		} else {
			throw new ResourceNotFoundException(AppMessage.IMAGE_NOT_FOUND);
		}
	}

	@PostMapping(value = "/products/{userId}/images/archive/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> archiveById(@PathVariable Long userId, @PathVariable Long id) {

		imageService.archiveById(userId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}

	@DeleteMapping(value = "/products/{userId}/images/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> deleteById(@PathVariable Long userId, @PathVariable Long id) {

		imageService.deleteById(userId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}
}
