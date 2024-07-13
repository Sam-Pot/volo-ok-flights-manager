package com.volook.flightsManager.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import flightsManager.Flights.Fare;

@Component
public class FareToFareDto implements Converter<com.volook.flightsManager.entities.Fare,flightsManager.Flights.Fare> {

	@Override
	public Fare convert(com.volook.flightsManager.entities.Fare source) {
		Fare fareDto = Fare.newBuilder()
				.setEditable(source.isEditable())
				.setId(source.getId().toString())
				.setModificationPrice(source.getModificationPrice())
				.setName(source.getName())
				.setPrice(source.getPrice())
				.build();
		return fareDto;
	}

}
