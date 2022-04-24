package com.pritanjalis.airbnb.controller;

import java.net.URI;
import java.util.*;

import com.pritanjalis.airbnb.domain.AirbnbProperty;
import com.pritanjalis.airbnb.domain.EntityExistsException;
import com.pritanjalis.airbnb.domain.EntityNotFoundException;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.pritanjalis.airbnb.exception.InvalidAirbnbPropertyTypeException;
import com.pritanjalis.airbnb.service.AirbnbPropertyManagementService;

@RestController
@Validated
@RequestMapping("/airbnb-property-management")
public class AirbnbPropertyManagementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AirbnbPropertyManagementController.class);

	@Autowired
	private AirbnbPropertyManagementService airbnbPropertyManagementService;

	@GetMapping("/airbnb")
	public List<AirbnbProperty> getAirbnbProperty() {
		LOGGER.info("Getting all AirbnbProperty using GET on /airbnb endpoint");
        return airbnbPropertyManagementService.getAllAirbnbProperty();
	}

	/**
	 * Find a AirbnbProperty by ID.
	 */
	@GetMapping("/airbnb/{id}")
	public ResponseEntity<AirbnbProperty> getAirbnbPropertyById(@PathVariable final Long id) {
		LOGGER.info("Getting AirbnbProperty with id {} using GET on /airbnb/{} endpoint", id, id);

		Optional<AirbnbProperty> result = airbnbPropertyManagementService.getAirbnbPropertyById(id);
		if (result != null && result.isPresent()) {
			AirbnbProperty airbnbProperty = result.get();
			return new ResponseEntity<AirbnbProperty>(airbnbProperty, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("AirbnbProperty", "id", id.toString());
		}
	}

	/**
	 * Find AirbnbProperty by property Id.
	 */
	@GetMapping("/airbnb/property/{propertyId}")
	public ResponseEntity<AirbnbProperty> getAirbnbPropertyByPropertyId(@PathVariable final String propertyId) {
		LOGGER.info("Getting AirbnbProperty with propertyId {} using GET on /airbnb/property/{} endpoint", propertyId,
				propertyId);
		Optional<AirbnbProperty> result = airbnbPropertyManagementService.getAirbnbPropertyByPropertyId(propertyId);
		if (result != null && result.isPresent()) {
			AirbnbProperty airbnbProperty = result.get();
			return new ResponseEntity<AirbnbProperty>(airbnbProperty, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("AirbnbProperty", "propertyId", propertyId);
		}
	}

	/**
	 * Find AirbnbProperty by {@link com.pritanjalis.airbnb.domain.AirbnbPropertyType} type.
	 */
	@GetMapping("/airbnb/propertytype/{airbnbPropertyType}")
	public List<AirbnbProperty> getAirbnbPropertyByAirbnbPropertyType(@PathVariable String airbnbPropertyType) {
		LOGGER.info("Getting AirbnbProperty with {} AirbnbPropertyType using GET on /airbnb/propertytype/{} endpoint",
				airbnbPropertyType, airbnbPropertyType);
		List<String> validData = new ArrayList<>();
		validData.add("manual");
		validData.add("automatic");
		validData.add("semiautomatic");
		if (validData.contains(airbnbPropertyType.toLowerCase())) {
			List<AirbnbProperty> result = null; //airbnbPropertyManagementService
					//.getAllAirbnbPropertyByAirbnbPropertyType(AirbnbPropertyType.valueOf(airbnbPropertyType));
			return result;
		} else {
			throw new InvalidAirbnbPropertyTypeException(
					String.format("Invalid Transmission type {}. Please select manual, automatic or semiautomatic",
							airbnbPropertyType));
		}
	}

	/**
	 * Get AirbnbProperty with BookingDetails
	 */
	@GetMapping("/airbnb/{id}/booking")
	public ResponseEntity<AirbnbProperty> getAirbnbPropertyWithBookingDetails(@PathVariable final Long id) {
		LOGGER.info("Getting AirbnbProperty with id {} using GET on / endpoint", id);
		Optional<AirbnbProperty> result = airbnbPropertyManagementService.getAirbnbPropertyById(id);
		if (result.isPresent()) {
			return new ResponseEntity<AirbnbProperty>(result.get(), HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("AirbnbProperty", "id", id.toString());
		}
	}

	/**
	 * Method to add a AirbnbProperty.
	 */
	@DeleteMapping("/airbnb/{id}")
	public ResponseEntity<?> deleteAirbnbProperty(@PathVariable final Long id) {
		LOGGER.info("Deleting AirbnbProperty with {} using DELETE", id);
		final Optional<AirbnbProperty> result = airbnbPropertyManagementService.getAirbnbPropertyById(id);
		if (result.isPresent()) {
			airbnbPropertyManagementService.deleteAirbnbProperty(id);
		} else {
			throw new EntityNotFoundException("AirbnbProperty", "id", id.toString());
		}
		return ResponseEntity.ok(HttpStatus.OK);
	}

	/**
	 * Method to add a AirbnbProperty.
	 */
	@PostMapping("/airbnb")
	public ResponseEntity<AirbnbProperty> addAirbnbProperty(@RequestBody AirbnbProperty newProperty) {
		LOGGER.info("Add new AirbnbProperty with {} using POST", newProperty.toString());
		String propertyId = newProperty.getPropertyId();
		Optional<AirbnbProperty> existingAirbnbProperty = airbnbPropertyManagementService
				.getAirbnbPropertyByPropertyId(propertyId);
		if (existingAirbnbProperty != null && existingAirbnbProperty.isPresent()) {
			throw new EntityExistsException("AirbnbProperty", "propertyId", propertyId);
		} else {
			airbnbPropertyManagementService.addAirbnbProperty(newProperty);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newProperty.getBnbId()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	/**
	 * Update an existing AirbnbProperty.
	 */
	@PutMapping("/airbnb")
	public ResponseEntity<AirbnbProperty> updateAirbnbProperty(@RequestBody final AirbnbProperty modifiedAirbnbProperty) {
		LOGGER.info("Update AirbnbProperty having ID {} with {}",
				modifiedAirbnbProperty.getBnbId(), modifiedAirbnbProperty.toString());
		boolean isUpdated = airbnbPropertyManagementService.updateAirbnbProperty(modifiedAirbnbProperty);
		if (isUpdated) {
			return ResponseEntity.ok(modifiedAirbnbProperty);
		} else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(modifiedAirbnbProperty.getPropertyId()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

}
