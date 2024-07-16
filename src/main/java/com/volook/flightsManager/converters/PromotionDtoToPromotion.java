package com.volook.flightsManager.converters;

import java.util.Date;
import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import flightsManager.Flights.Promotion;

@Component
public class PromotionDtoToPromotion implements Converter<flightsManager.Flights.Promotion, com.volook.flightsManager.entities.Promotion> {

	@Override
	public com.volook.flightsManager.entities.Promotion convert(Promotion source) {
		com.volook.flightsManager.entities.Promotion promotion = new com.volook.flightsManager.entities.Promotion();
		if(source.getId()!=null && !source.getId().isEmpty()) {
			promotion.setId(UUID.fromString(source.getId()));
		}
		promotion.setName(source.getName());
		promotion.setStartDate(new Date(source.getStartDate()));
		promotion.setEndDate(new Date(source.getEndDate()));
		promotion.setDiscountPercentage(source.getDiscountPercentage());
		promotion.setOnlyForLoyalCustomer(source.getOnlyForLoyalCustomer());
		return promotion;
	}



}
