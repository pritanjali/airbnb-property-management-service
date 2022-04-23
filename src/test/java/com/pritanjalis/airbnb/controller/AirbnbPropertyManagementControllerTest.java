package com.pritanjalis.airbnb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.*;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pritanjalis.airbnb.domain.airbnb.AirbnbProperty;
import com.pritanjalis.airbnb.domain.airbnb.AirbnbPropertyType;
import com.pritanjalis.airbnb.service.AirbnbPropertyManagementService;

@SpringBootTest
@AutoConfigureMockMvc

public class AirbnbPropertyManagementControllerTest {

	@Autowired
	private MockMvc mockMvc;


	@MockBean
	AirbnbPropertyManagementService mockAirbnbPropertyManagementService;


	@Test
	public void testGetAirbnbPropertyWithId() throws Exception {
		String expectedJson = "{\"bnbId\":null,\"propertyId\":\"IRE-WH-111\",\"location\":\"Westmeath\",\"numOfBedroom\":3,\"numOfBathroom\":2,\"guests\":6,\"type\":\"FLAT\",\"rate\":150.0}";
		AirbnbProperty airbnbProperty = new AirbnbProperty("IRE-WH-111", "Westmeath", 3, 2, 6, AirbnbPropertyType.FLAT, 150.00f);
		when(mockAirbnbPropertyManagementService.getAirbnbPropertyById((anyLong()))).thenReturn(Optional.of(airbnbProperty));
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/airbnb-property-management/airbnb/1")
				.accept(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertNotNull(result.getResponse().getContentAsString());
		System.out.print(result.getResponse().getContentAsString());
		JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetAirbnbPropertyWithPropertyId() throws Exception {
		String expectedJson = "{\"bnbId\":null,\"propertyId\":\"IRE-WH-111\",\"location\":\"Westmeath\",\"numOfBedroom\":3,\"numOfBathroom\":2,\"guests\":6,\"type\":\"FLAT\",\"rate\":150.0}";
		AirbnbProperty airbnbProperty = new AirbnbProperty("IRE-WH-111", "Westmeath", 3, 2, 6, AirbnbPropertyType.FLAT, 150.00f);
		Optional<AirbnbProperty> resultAirbnbProperty = Optional.of(airbnbProperty);
		when(mockAirbnbPropertyManagementService.getAirbnbPropertyByPropertyId(anyString())).thenReturn(resultAirbnbProperty);
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/airbnb-property-management/airbnb/property/IRE-WH-111")
				.accept(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertNotNull(result.getResponse().getContentAsString());
		System.out.print(result.getResponse().getContentAsString());
		JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetAllAirbnbProperty() throws Exception {
		AirbnbProperty airbnbProperty = new AirbnbProperty("IRE-WH-111", "Westmeath", 3, 2, 6, AirbnbPropertyType.FLAT, 150.00f);
		List<AirbnbProperty> airbnbPropertyList = new ArrayList<>();
		airbnbPropertyList.add(airbnbProperty);
		when(mockAirbnbPropertyManagementService.getAllAirbnbProperty()).thenReturn(airbnbPropertyList);
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/airbnb-property-management/airbnb")
				.accept(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.print(result.getResponse().getContentAsString());
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertNotNull(result.getResponse().getContentAsString());
	}


}
