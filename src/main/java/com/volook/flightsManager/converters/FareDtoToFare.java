package com.volook.flightsManager.converters;

import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import flightsManager.Flights.Fare;

@Component
public class FareDtoToFare implements Converter<flightsManager.Flights.Fare, com.volook.flightsManager.entities.Fare> {

	@Override
	public com.volook.flightsManager.entities.Fare convert(Fare source) {
		com.volook.flightsManager.entities.Fare fare = new com.volook.flightsManager.entities.Fare();
		fare.setEditable(source.getEditable());
		if(source.getId()!=null && !source.getId().isEmpty()) {
			fare.setId(UUID.fromString(source.getId()));
		}
		fare.setModificationPrice(source.getModificationPrice());
		fare.setName(source.getName());
		fare.setPrice(source.getPrice());
		return fare;
	}



}
