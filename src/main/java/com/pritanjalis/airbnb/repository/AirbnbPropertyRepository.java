package com.pritanjalis.airbnb.repository;

import java.util.List;
import java.util.Optional;

import com.pritanjalis.airbnb.domain.AirbnbProperty;
import org.springframework.data.jpa.repository.JpaRepository;


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
