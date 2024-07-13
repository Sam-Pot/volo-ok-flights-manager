package com.volook.flightsManager.converters;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import flightsManager.Flights.Fare;
import flightsManager.Flights.Flight;

@Component
public class FlightToFlightDto implements Converter<com.volook.flightsManager.entities.Flight,flightsManager.Flights.Flight> {

	@Autowired
	private PromotionToPromotionDto promotionToPromotionDto;
	@Autowired
	private FareToFareDto fareToFareDto;
	@Autowired
	private AirportToAirportDto airportToAirportDto;
	
	@Override
	public Flight convert(com.volook.flightsManager.entities.Flight source) {
		List<Fare> fares = new LinkedList<>();
		for(com.volook.flightsManager.entities.Fare elem: source.getFares()) {
			fares.add(fareToFareDto.convert(elem));
		}	
		Flight flightDto = Flight.newBuilder()
				.setId(source.getId().toString())
				.setName(source.getName())
				.setStartDateTime(source.getStartDateTime().getTime())
				.setEndDateTime(source.getEndDateTime().getTime())
				.setDistance(source.getDistance())
				.setFrequencyType(flightsManager.Flights.Frequency.valueOf(source.getFrequencyType().toString()))
				.setFrequency(source.getFrequency())
				.setDeparture(airportToAirportDto.convert(source.getDeparture()))
				.setDestination(airportToAirportDto.convert(source.getDestination()))
				.setPromotion(promotionToPromotionDto.convert(source.getPromotion()))
				.addAllFares(fares)				
				.build();
		return flightDto;
	}

}
