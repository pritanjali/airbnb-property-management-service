package com.pritanjalis.airbnb.service;

import java.util.List;
import java.util.Optional;

import com.pritanjalis.airbnb.domain.AirbnbProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.pritanjalis.airbnb.repository.AirbnbPropertyRepository;

@Service
public class AirbnbPropertyManagementService {

	@Autowired
	private AirbnbPropertyRepository airbnbPropertyRepository;

	public AirbnbProperty addAirbnbProperty(final AirbnbProperty property) {
		return airbnbPropertyRepository.save(property);
	}

	public boolean updateAirbnbProperty(final AirbnbProperty updatedProperty) {
		boolean isPropertyUpdated = false;
		if (airbnbPropertyRepository.findById(updatedProperty.getBnbId()).isPresent()) {
			isPropertyUpdated = true;
		}
		airbnbPropertyRepository.save(updatedProperty);
		return isPropertyUpdated;
	}

	public void deleteAirbnbProperty(final Long airbnbId) {
		airbnbPropertyRepository.deleteById(airbnbId);
	}
	
	public List<AirbnbProperty> getAllAirbnbProperty() {
		return airbnbPropertyRepository.findAll();
	}
	
	public Optional<AirbnbProperty> getAirbnbPropertyById(final Long propertyId) {
		return airbnbPropertyRepository.findById(propertyId);
	}

	
	public Page<AirbnbProperty> findAllPage(Pageable pageable) {
		return airbnbPropertyRepository.findAll(pageable);
	}
	
	public Optional<AirbnbProperty> getAirbnbPropertyByPropertyId(final String registrationId) {
		return airbnbPropertyRepository.findByPropertyId(registrationId);
	}
}
