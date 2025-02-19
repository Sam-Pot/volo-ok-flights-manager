package com.volook.flightsManager.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import flightsManager.Flights.Airport;

@Component
public class AirportToAirportDto implements Converter<com.volook.flightsManager.entities.Airport,flightsManager.Flights.Airport> {

	@Override
	public Airport convert(com.volook.flightsManager.entities.Airport source) {
		Airport airport = Airport.newBuilder()
				.setId(source.getId().toString())
				.setName(source.getName())
				.setIata(source.getIata())
				.setLatitude(source.getLatitude())
				.setLongitude(source.getLongitude())
				.build();
		return airport;
	}

}
