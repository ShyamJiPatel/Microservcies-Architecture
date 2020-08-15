package com.shyam.commonlib;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppProperties {

	public static String APP_NAME;
	public static String FILE_UPLOAD_PATH;

	@Value("${file.upload.baseDir}")
	public void setFileUploadPath(String path) {
		AppProperties.FILE_UPLOAD_PATH = path;
	}

	@Value("${spring.application.name}")
	public void setAppName(String appName) {
		AppProperties.APP_NAME = appName;
	}

	@PostConstruct
	public void print() {
		log.info("APP_NAME: " + APP_NAME);
		log.info("FILE_UPLOAD_PATH: " + FILE_UPLOAD_PATH);
	}

}
