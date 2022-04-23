package com.pritanjalis.airbnb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pritanjalis.airbnb.domain.airbnb.*;

/**
 * Repository class for {@link AirbnbProperty} extends
 * {@link org.springframework.data.jpa.repository.JpaRepository}.
 *
 */
public interface AirbnbPropertyRepository extends JpaRepository<AirbnbProperty, Long> {

	//List<AirbnbProperty> findByPropertyType(AirbnbPropertyType propertyType);

	List<AirbnbProperty> findByLocation(String location);
	
	Optional<AirbnbProperty> findByPropertyId(String propertyId);
}
