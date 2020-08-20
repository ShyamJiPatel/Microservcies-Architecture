package com.shyam.api.productservice.constants;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppMessage {

	public static String RESOURCE_NOT_FOUND;
	public static String USER_NOT_FOUND;
	public static String IMAGE_NOT_FOUND;
	public static String PRODUCT_NOT_FOUND;
	public static String CATEGORY_NOT_FOUND;
	public static String PARENT_CATEGORY_NOT_FOUND;

	@Value("${app.message.resource-not-found}")
	public void setResoureNotFound(String message) {
		AppMessage.RESOURCE_NOT_FOUND = message;
	}

	@Value("${app.message.user-not-found}")
	public void setUserNotFound(String message) {
		AppMessage.USER_NOT_FOUND = message;
	}

	@Value("${app.message.image-not-found}")
	public void setImageNotFound(String message) {
		AppMessage.IMAGE_NOT_FOUND = message;
	}

	@Value("${app.message.product-not-found}")
	public void setProductNotFound(String message) {
		AppMessage.PRODUCT_NOT_FOUND = message;
	}

	@Value("${app.message.category-not-found}")
	public void setCategoryNotFound(String message) {
		AppMessage.CATEGORY_NOT_FOUND = message;
	}

	@Value("${app.message.parent-category-not-found}")
	public void setParentCategoryNotFound(String message) {
		AppMessage.PARENT_CATEGORY_NOT_FOUND = message;
	}

	@PostConstruct
	public void print() {
		log.info("RESOURCE_NOT_FOUND: " + RESOURCE_NOT_FOUND);
		log.info("USER_NOT_FOUND: " + USER_NOT_FOUND);
		log.info("IMAGE_NOT_FOUND: " + IMAGE_NOT_FOUND);
		log.info("PRODUCT_NOT_FOUND: " + PRODUCT_NOT_FOUND);
		log.info("CATEGORY_NOT_FOUND: " + CATEGORY_NOT_FOUND);
		log.info("PARENT_CATEGORY_NOT_FOUND: " + PARENT_CATEGORY_NOT_FOUND);
	}
}
