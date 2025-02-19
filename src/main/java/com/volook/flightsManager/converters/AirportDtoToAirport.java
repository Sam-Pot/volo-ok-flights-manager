package com.volook.flightsManager.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import flightsManager.Flights.Airport;

@Component
public class AirportDtoToAirport  implements Converter<flightsManager.Flights.Airport, com.volook.flightsManager.entities.Airport> {

	@Override
	public com.volook.flightsManager.entities.Airport convert(Airport source) {
		com.volook.flightsManager.entities.Airport departure = new com.volook.flightsManager.entities.Airport();
		departure.setId(source.getId());
		departure.setName(source.getName());
		departure.setIata(source.getIata());
		departure.setLatitude(source.getLatitude());
		departure.setLongitude(source.getLongitude());
		return departure;
	}
}
