package com.volook.flightsManager.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import flightsManager.Flights.Promotion;
@Component
public class PromotionToPromotionDto implements Converter<com.volook.flightsManager.entities.Promotion,flightsManager.Flights.Promotion> {

	@Override
	public Promotion convert(com.volook.flightsManager.entities.Promotion source) {
		Promotion promotionDto = Promotion.newBuilder()
				.setId(source.getId().toString())
				.setName(source.getName())
				.setDiscountPercentage(source.getDiscountPercentage())
				.setEndDate(source.getEndDate().getTime())
				.setStartDate(source.getStartDate().getTime())
				.setOnlyForLoyalCustomer(source.isOnlyForLoyalCustomer())
				.build();
		return promotionDto;
	}

}