package com.hiddin.pushnotification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PushNotificationController {

	@PostMapping("/send")
	public ResponseEntity<Response> sendNotification(@RequestParam("token") String token,
			@RequestParam("message") String message) {
		if (!"".equals(token) && !"".equals(message)) {
			FcmClient.sendNotification(message, token, 1);
		}
		Response res = new Response();
		res.setMessage("success");
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@GetMapping("/")
	public String sayHello() {
		return "Hello User";
	}

}