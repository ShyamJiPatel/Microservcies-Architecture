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

import com.shyam.api.userservice.entity.Address;
import com.shyam.api.userservice.entity.CustomResponse;
import com.shyam.api.userservice.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping(value = "/users/{userId}/address", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> saveUpdate(@Valid @RequestBody Address address, @PathVariable Long userId) {

		Address dbAddress = addressService.saveUpdate(address, userId);

		return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse(dbAddress));
	}

	@GetMapping(value = "/users/{userId}/address/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findById(@PathVariable Long userId, @PathVariable Long id) {

		Address dbAddress = addressService.findById(userId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(dbAddress));
	}

	@GetMapping(value = "/users/{userId}/address", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> findAll(@PathVariable Long userId) {

		List<Address> allAddress = addressService.findAll(userId);

		if (allAddress != null && allAddress.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(allAddress));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(new ArrayList<Address>()));
		}
	}

	@PostMapping(value = "/users/{userId}/address/archive/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> archiveById(@PathVariable Long userId, @PathVariable Long id) {

		addressService.archiveById(userId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}

	@DeleteMapping(value = "/users/{userId}/address/{id}", produces = { MediaType.APPLICATION_JSON })
	public ResponseEntity<CustomResponse> deleteById(@PathVariable Long userId, @PathVariable Long id) {

		addressService.deleteById(userId, id);

		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse());
	}
}
