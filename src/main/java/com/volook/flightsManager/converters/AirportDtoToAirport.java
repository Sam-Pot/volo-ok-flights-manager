package com.volook.flightsManager.converters;

import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import flightsManager.Flights.Airport;

@Component
public class AirportDtoToAirport  implements Converter<flightsManager.Flights.Airport, com.volook.flightsManager.entities.Airport> {

	@Override
	public com.volook.flightsManager.entities.Airport convert(Airport source) {
		com.volook.flightsManager.entities.Airport departure = new com.volook.flightsManager.entities.Airport();
		departure.setId(UUID.fromString(source.getId()));
		departure.setName(source.getName());
		departure.setMunicipality(source.getMunicipality());
		departure.setMunicipalityCode(source.getMunicipalityCode());
		departure.setNationalCode(source.getNationalCode());
		return departure;
	}
}
