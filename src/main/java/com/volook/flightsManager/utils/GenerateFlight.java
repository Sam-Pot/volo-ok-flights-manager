package com.volook.flightsManager.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import com.volook.flightsManager.entities.Flight;

import flightsManager.Flights.Airport;
import flightsManager.Flights.AvailableFlight;
import flightsManager.Flights.Fare;
import flightsManager.Flights.Promotion;

public class GenerateFlight implements Callable<List<AvailableFlight>> {

	private Flight flight;
	private long selectedDate;
	private final long dayAfterSelectedDate;
	
	private final long HOURLY_FACTOR = (60 * 60 * 1000);
	private final long DAILY_FACTOR = HOURLY_FACTOR * 24;
	private final long WEEKLY_FACTOR = DAILY_FACTOR * 7;
	private final long MONTHLY_FACTOR = DAILY_FACTOR * 30;
	
	private final long[] timeFactor = {HOURLY_FACTOR, DAILY_FACTOR, WEEKLY_FACTOR,MONTHLY_FACTOR};
	
	public GenerateFlight(Flight flight, long selectedDate) {
		if (flight == null) {
			throw new IllegalArgumentException();
		}
		this.flight = flight;
		this.selectedDate = selectedDate;
		this.dayAfterSelectedDate = selectedDate + DAILY_FACTOR;
	}

	@Override
	public List<AvailableFlight> call() throws Exception {
		List<AvailableFlight> availableFlights = new LinkedList<>();
		long curDate = flight.getStartDateTime().getTime();
		while (curDate <= flight.getEndDateTime().getTime()) {
			long offset = timeFactor[flight.getFrequencyType().ordinal()];
			long nextDepartureDate = curDate + offset;
			if(nextDepartureDate>=selectedDate && nextDepartureDate<dayAfterSelectedDate) {
				Airport departure = Airport.newBuilder()
						.setId(flight.getDeparture().getId().toString())
						.setName(flight.getDeparture().getName())
						.setMunicipality(flight.getDeparture().getMunicipality())
						.setMunicipalityCode(flight.getDeparture().getMunicipalityCode())
						.setNationalCode(flight.getDeparture().getNationalCode())
						.build();
				Airport destination = Airport.newBuilder()
						.setId(flight.getDestination().getId().toString())
						.setName(flight.getDestination().getName())
						.setMunicipality(flight.getDestination().getMunicipality())
						.setMunicipalityCode(flight.getDestination().getMunicipalityCode())
						.setNationalCode(flight.getDestination().getNationalCode())
						.build();
				Promotion promotion = Promotion.newBuilder()
						.setId(flight.getPromotion().getId().toString())
						.setName(flight.getPromotion().getName())
						.setDiscountPercentage(flight.getPromotion().getDiscountPercentage())
						.setEndDate(flight.getPromotion().getEndDate().getTime())
						.setStartDate(flight.getPromotion().getStartDate().getTime())
						.setOnlyForLoyalCustomer(flight.getPromotion().isOnlyForLoyalCustomer())
						.build();
				List<Fare> fares = new LinkedList<>();
				for(Fare fare: fares) {
					Fare curFare = Fare.newBuilder()
							.setEditable(fare.getEditable())
							.setId(fare.getId().toString())
							.setModificationPrice(fare.getModificationPrice())
							.setName(fare.getName())
							.setPrice(fare.getPrice())
							.build();
					fares.add(curFare);
				}
				AvailableFlight availableFlight = AvailableFlight.newBuilder()
						.setId(flight.getId().toString())
						.setName(flight.getName())
						.setDepartureDateTime(nextDepartureDate)
						.setDistance(flight.getDistance())
						.setDeparture(departure)
						.setDestination(destination)
						.setPromotion(promotion)
						.addAllFares(fares)
						.build();
				availableFlights.add(availableFlight);
				curDate = nextDepartureDate;
			}else {
				break;
			}
		}
		return availableFlights;
	}

}
