package com.hiddin.pushnotification;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class PushnotificationApplication {

	private static Logger logger = LoggerFactory.getLogger(PushnotificationApplication.class);

	@Autowired
	private FcmSettings settings;

	public static void main(String[] args) {
		SpringApplication.run(PushnotificationApplication.class, args);
	}

	@PostConstruct
	public void initPushNotification() {

		try {

			InputStream serviceAccount = PushnotificationApplication.class.getClassLoader()
					.getResourceAsStream(settings.getServiceAccountFile());
			if (serviceAccount == null) {
				serviceAccount = new FileInputStream(settings.getServiceAccountFile());
			}

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}

}
