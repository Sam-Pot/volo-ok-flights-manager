package com.volook.flightsManager.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import flightsManager.Flights;
import flightsManager.Flights.Flight;
import flightsManager.Flights.FlightDto;

import com.volook.flightsManager.converters.PromotionToPromotionDto;
import com.volook.flightsManager.entities.Fare;
import com.volook.flightsManager.entities.Promotion;
import com.volook.flightsManager.utils.Frequency;

@SpringBootTest
public class FlightServiceTest {

	@Autowired
	private FlightService flightService;

	
	@Test
	void testSave() {
		Iterable<String> lista = new LinkedList<>();
		com.volook.flightsManager.entities.Flight f = null;
		com.volook.flightsManager.entities.Flight f2 = null;
		FlightDto dto = FlightDto.newBuilder()
				.setName("volo")
				.setStartDateTime(new Date().getTime())
				.setEndDateTime(new Date().getTime())
				.setDistance(1234323)
				.setFrequencyType(Flights.Frequency.HOURLY)
				.setFrequency(3)
				.setSeats(12)
				.setDeparture("Milano MXP")
				.setDestination("LS Airport")
				.setPromotion("fakeId")
				.addAllFares(lista)				
				.build();
		try {
			f = this.flightService.saveOrUpdate(dto);
			f2 = this.flightService.saveOrUpdate(null);
			assertThat(f.getId().toString()).isNotNull();
			assertThat(f).isNull();;
		}catch(Exception e) {
			
		}
	}
	
	@Test
	void testFindOne() {
		try {
			Iterable<String> lista = new LinkedList<>();
			com.volook.flightsManager.entities.Flight f = null;
			com.volook.flightsManager.entities.Flight f2 = null;
			FlightDto dto = FlightDto.newBuilder()
					.setName("volo")
					.setStartDateTime(new Date().getTime())
					.setEndDateTime(new Date().getTime())
					.setDistance(1234323)
					.setFrequencyType(Flights.Frequency.HOURLY)
					.setFrequency(3)
					.setSeats(12)
					.setDeparture("Milano MXP")
					.setDestination("LS Airport")
					.setPromotion("fakeId")
					.addAllFares(lista)				
					.build();
			f = this.flightService.saveOrUpdate(dto);
			com.volook.flightsManager.entities.Flight found = this.flightService.findOne(f.getId().toString());
			assertThat(f).isNotNull();
		}catch(Exception e) {
			
		}
	}
}
