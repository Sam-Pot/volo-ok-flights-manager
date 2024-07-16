package com.volook.flightsManager.converters;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import flightsManager.Flights.Fare;
import flightsManager.Flights.Flight;

@Component
public class FlightDtoToFlight implements Converter<flightsManager.Flights.Flight, com.volook.flightsManager.entities.Flight> {

	@Autowired
	private PromotionDtoToPromotion promotionDtoToPromotion;
	@Autowired
	private FareDtoToFare fareDtoToFare;
	@Autowired
	private AirportDtoToAirport airportDtoToAirport;
	
	@Override
	public com.volook.flightsManager.entities.Flight convert(Flight source) {
		
		List<com.volook.flightsManager.entities.Fare> fares = new LinkedList<>();
		for(Fare elem: source.getFaresList()) {
			fares.add(fareDtoToFare.convert(elem));
		}
		
		com.volook.flightsManager.entities.Flight flight = new com.volook.flightsManager.entities.Flight();
		if(source.getId()!=null && !source.getId().isEmpty()) {
			flight.setId(UUID.fromString(source.getId()));
		}
		flight.setName(source.getName());
		flight.setStartDateTime(new Date(source.getStartDateTime()));
		flight.setEndDateTime(new Date(source.getEndDateTime()));
		flight.setDistance(source.getDistance());
		flight.setFrequencyType(com.volook.flightsManager.utils.Frequency.valueOf(source.getFrequencyType().toString()));
		flight.setFrequency(source.getFrequency());
		flight.setSeats(source.getSeats());
		flight.setDeparture(airportDtoToAirport.convert(source.getDeparture()));
		flight.setDestination(airportDtoToAirport.convert(source.getDestination()));
		flight.setPromotion(promotionDtoToPromotion.convert(source.getPromotion()));
		flight.setFares(fares);
		return flight;
	}



}
