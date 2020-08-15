package com.shyam.api.productservice.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.api.productservice.constants.AppMessage;
import com.shyam.api.productservice.entity.Category;
import com.shyam.api.productservice.service.CategoryService;
import com.shyam.commonlib.entity.CustomResponse;
import com.shyam.commonlib.exception.ResourceNotFoundException;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping(value = "/categories", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> saveUpdate(@Valid @RequestBody Category category) {

		Category dbCategory = categoryService.saveUpdate(category);

		return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse(dbCategory));
	}

	@GetMapping(value = "/categories/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findById(@PathVariable Long id) {

		Optional<Category> category = categoryService.findById(id);

		if (category.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(category.get()));
		} else {
			throw new ResourceNotFoundException(AppMessage.CATEGORY_NOT_FOUND);
		}
	}

	@GetMapping(value = "/categories", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findAll() {

		List<Category> allCategory = categoryService.findAll();

		if (allCategory != null && allCategory.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(allCategory));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(new ArrayList<Category>()));
		}
	}

	@PostMapping(value = "/categories/archive/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> archiveById(@PathVariable Long id) {

		categoryService.archiveById(id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}

	@DeleteMapping(value = "/categories/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> deleteById(@PathVariable Long id) {

		categoryService.deleteById(id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}

	@PostMapping(value = "/categories/{categoryId}/subcategories", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> saveUpdate(@PathVariable Long categoryId,
			@Valid @RequestBody Category subcategory) {

		Category dbCategory = categoryService.saveUpdateSubcategory(categoryId, subcategory);

		return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse(dbCategory));
	}

	@GetMapping(value = "/categories/{categoryId}/subcategories/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findById(@PathVariable Long categoryId, @PathVariable Long id) {

		Category category = categoryService.findById(categoryId, id);

		if (category != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(category));
		} else {
			throw new ResourceNotFoundException(AppMessage.PARENT_CATEGORY_NOT_FOUND);
		}
	}

	@GetMapping(value = "/categories/{categoryId}/subcategories", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findAll(@PathVariable Long categoryId) {

		List<Category> allCategory = categoryService.findAll(categoryId);

		if (allCategory != null && allCategory.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(allCategory));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(new ArrayList<Category>()));
		}
	}

	@PostMapping(value = "/categories/{categoryId}/subcategories/archive/{id}", produces = {
			MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> archiveById(@PathVariable Long categoryId, @PathVariable Long id) {

		categoryService.archiveById(categoryId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}

	@DeleteMapping(value = "/categories/{categoryId}/subcategories/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> deleteById(@PathVariable Long categoryId, @PathVariable Long id) {

		categoryService.deleteById(categoryId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}
}
