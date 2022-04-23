package com.pritanjalis.airbnb.controller;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pritanjalis.airbnb.domain.exceptions.*;
import com.pritanjalis.airbnb.exception.InvalidAirbnbPropertyTypeException;

@ControllerAdvice
public class AirbnbPropertyManagementControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleAirbnbPropertyNotFoundException(final EntityNotFoundException cnfExc,
			final WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", cnfExc.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<Object> handleAirbnbPropertyExistsScenario(final EntityExistsException exception,
			final WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", exception.getMessage());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(InvalidAirbnbPropertyTypeException.class)
	public ResponseEntity<Object> handleInvalidAirbnbPropertyTypeScenario(final InvalidAirbnbPropertyTypeException exception,
			final WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", exception.getMessage());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoEntityDataFoundException.class)
	public ResponseEntity<Object> handleNoDataFoundException(final NoEntityDataFoundException exception,
			final WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", exception.getMessage());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		body.put("errors", errors);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	

}
