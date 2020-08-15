package com.shyam.commonlib.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shyam.commonlib.entity.CustomResponse;
import com.shyam.commonlib.exception.ResourceNotFoundException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public final ResponseEntity<CustomResponse> handleAllExceptions(EmptyResultDataAccessException ex, WebRequest request) {
		CustomResponse lErrorResponse = new CustomResponse("Resource not found", request.getDescription(false), false);
		return new ResponseEntity<CustomResponse>(lErrorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<CustomResponse> handleAllExceptions(Exception ex, WebRequest request) {
		CustomResponse lErrorResponse = new CustomResponse(ex.getMessage(), request.getDescription(false), false);
		return new ResponseEntity<CustomResponse>(lErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<CustomResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		CustomResponse lErrorResponse = new CustomResponse(ex.getMessage(), request.getDescription(false), false);
		return new ResponseEntity<CustomResponse>(lErrorResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomResponse lErrorResponse = new CustomResponse("Validation Failed", ex.getBindingResult().toString(),
				false);
		return new ResponseEntity<Object>(lErrorResponse, HttpStatus.BAD_REQUEST);
	}
}
