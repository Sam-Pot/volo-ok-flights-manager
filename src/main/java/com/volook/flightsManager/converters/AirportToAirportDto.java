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
				.setMunicipality(source.getMunicipality())
				.setMunicipalityCode(source.getMunicipalityCode())
				.setNationalCode(source.getNationalCode())
				.build();
		return airport;
	}

}
