package com.volook.flightsManager.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.volook.flightsManager.entities.Airport;

@SpringBootTest
public class AirportServicesTest {

	@Autowired
	private AirportService airportService;
	
	@Test
	void testFindAll() {
		List<Airport> list = airportService.findAll();
		assertThat(list).isNotEmpty();
	}
	
}
