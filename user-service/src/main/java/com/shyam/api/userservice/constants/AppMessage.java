package com.shyam.api.userservice.constants;

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
	public static String ADDRESS_NOT_FOUND;

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

	@Value("${app.message.address-not-found}")
	public void setAddressNotFound(String message) {
		AppMessage.ADDRESS_NOT_FOUND = message;
	}

	@PostConstruct
	public void print() {
		log.info("RESOURCE_NOT_FOUND: " + RESOURCE_NOT_FOUND);
		log.info("USER_NOT_FOUND: " + USER_NOT_FOUND);
		log.info("IMAGE_NOT_FOUND: " + IMAGE_NOT_FOUND);
		log.info("ADDRESS_NOT_FOUND: " + ADDRESS_NOT_FOUND);
	}
}
