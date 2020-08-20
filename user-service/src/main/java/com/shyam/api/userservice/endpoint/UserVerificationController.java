package com.shyam.api.userservice.endpoint;

import java.util.ArrayList;
import java.util.List;

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

import com.shyam.api.userservice.entity.UserVerificationStatus;
import com.shyam.api.userservice.service.UserVerificationService;
import com.shyam.commonlib.entity.CustomResponse;

@RestController
public class UserVerificationController {

	@Autowired
	private UserVerificationService userVerificationService;

	@PostMapping(value = "/users/{userId}/verificationstatus", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> saveUpdate(@Valid @RequestBody UserVerificationStatus verificationstatus,
			@PathVariable Long userId) {

		UserVerificationStatus dbUserVerification = userVerificationService.saveUpdate(verificationstatus, userId);

		return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse(dbUserVerification));
	}

	@GetMapping(value = "/users/{userId}/verificationstatus/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findById(@PathVariable Long userId, @PathVariable Long id) {

		UserVerificationStatus dbUserVerification = userVerificationService.findById(userId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(dbUserVerification));
	}

	@GetMapping(value = "/users/{userId}/verificationstatus", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findAll(@PathVariable Long userId) {

		List<UserVerificationStatus> allVerificationStatus = userVerificationService.findAll(userId);

		if (allVerificationStatus != null && allVerificationStatus.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(allVerificationStatus));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new CustomResponse(new ArrayList<UserVerificationStatus>()));
		}
	}

	@PostMapping(value = "/users/{userId}/verificationstatus/archive/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> archiveById(@PathVariable Long userId, @PathVariable Long id) {

		userVerificationService.archiveById(userId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}

	@DeleteMapping(value = "/users/{userId}/verificationstatus/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> deleteById(@PathVariable Long userId, @PathVariable Long id) {

		userVerificationService.deleteById(userId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}
}
