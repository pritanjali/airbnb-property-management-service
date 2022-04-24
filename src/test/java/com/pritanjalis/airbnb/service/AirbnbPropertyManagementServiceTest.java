package com.pritanjalis.airbnb.service;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import com.pritanjalis.airbnb.domain.AirbnbProperty;
import com.pritanjalis.airbnb.domain.AirbnbPropertyType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.pritanjalis.airbnb.repository.AirbnbPropertyRepository;

@ExtendWith(SpringExtension.class)
public class AirbnbPropertyManagementServiceTest {

    @Mock
    private AirbnbPropertyRepository airbnbPropertyRepository; 

    @InjectMocks
    private AirbnbPropertyManagementService airbnbPropertyManagementService;

    @Test
    void testGetAllAirbnbProperty() {
    	AirbnbProperty airbnbProperty = new AirbnbProperty("IRE-WH-111", "Westmeath", 3, 2, 6, AirbnbPropertyType.FLAT, 150.00f);
        final List<AirbnbProperty> airbnbPropertyList = Arrays.asList(
        		airbnbProperty
        );
        when(airbnbPropertyRepository.findAll()).thenReturn(airbnbPropertyList); 
        final List<AirbnbProperty>  resultAirbnbPropertyList = airbnbPropertyManagementService.getAllAirbnbProperty();
        assertNotNull(resultAirbnbPropertyList);
        assertThat(resultAirbnbPropertyList.iterator().hasNext(), is(true));
    }

    @Test
    void testGetAllAirbnbProperty_whenNoAirbnbPropertyExists() {
        when(airbnbPropertyRepository.findAll()).thenReturn(new ArrayList<>());
        final List<AirbnbProperty> airbnbPropertyList = airbnbPropertyManagementService.getAllAirbnbProperty();
        assertNotNull(airbnbPropertyList);
        assertThat(airbnbPropertyList.size(), is(0));
    }

    @Test
    void testGetAirbnbPropertyWithId() {
    	AirbnbProperty airbnbProperty = new AirbnbProperty("IRE-WH-111", "Westmeath", 3, 2, 6, AirbnbPropertyType.FLAT, 150.00f);
        Long id = 5L;
        when(airbnbPropertyRepository.findById(id)).thenReturn(Optional.of(airbnbProperty));
        final Optional<AirbnbProperty> optionalAirbnbProperty = airbnbPropertyManagementService.getAirbnbPropertyById(id);
        final AirbnbProperty resultAirbnbProperty = optionalAirbnbProperty.get();
        assertNotNull(resultAirbnbProperty);
        assertThat(resultAirbnbProperty.getPropertyId(), is("IRE-WH-111"));
    }

    @Test
    void testGetAirbnbPropertyWithInvalidId() {
    	Optional<AirbnbProperty> airbnbPropertyWithInvalidId = airbnbPropertyManagementService.getAirbnbPropertyById(13L);
    	assertThat(airbnbPropertyWithInvalidId.isPresent(), is(Boolean.FALSE));
    	assertThrows(NoSuchElementException.class, () -> airbnbPropertyWithInvalidId.get());
    }

    @Test
    void testCreateAirbnbProperty() {
    	AirbnbProperty airbnbProperty = new AirbnbProperty("IRE-WH-111", "Westmeath", 3, 2, 6, AirbnbPropertyType.FLAT, 150.00f);
    	when(airbnbPropertyRepository.save(any(AirbnbProperty.class))).thenReturn(airbnbProperty);
        final AirbnbProperty newAirbnbProperty = airbnbPropertyManagementService.addAirbnbProperty(airbnbProperty);
        verify(airbnbPropertyRepository, times(1)).save(any(AirbnbProperty.class));
        assertNotNull(newAirbnbProperty);
    }
}
