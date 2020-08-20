package com.shyam.api.userservice.endpoint;

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

import com.shyam.api.userservice.constants.AppMessage;
import com.shyam.api.userservice.entity.UserDetails;
import com.shyam.api.userservice.service.UserService;
import com.shyam.commonlib.entity.CustomResponse;
import com.shyam.commonlib.exception.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/users", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> saveUpdate(@Valid @RequestBody UserDetails user) {

		UserDetails dbUser = userService.saveUpdate(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse(dbUser));
	}

	@GetMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findById(@PathVariable Long id) {

		Optional<UserDetails> user = userService.findById(id);

		if (user.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(user.get()));
		} else {
			throw new UserNotFoundException(AppMessage.USER_NOT_FOUND);
		}
	}

	@GetMapping(value = "/users", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findAll() {

		List<UserDetails> allUser = userService.findAll();

		if (allUser != null && allUser.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(allUser));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(new ArrayList<UserDetails>()));
		}
	}

	@PostMapping(value = "/users/archive/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> archiveById(@PathVariable Long id) {

		userService.archiveById(id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}

	@DeleteMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> deleteById(@PathVariable Long id) {

		userService.deleteById(id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}
}
