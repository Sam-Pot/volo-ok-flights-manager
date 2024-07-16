package com.volook.flightsManager.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volook.flightsManager.entities.Airport;
import com.volook.flightsManager.entities.Fare;
import com.volook.flightsManager.entities.Flight;
import com.volook.flightsManager.entities.Promotion;
import com.volook.flightsManager.repositories.AirportRepository;
import com.volook.flightsManager.repositories.FareRepository;
import com.volook.flightsManager.repositories.FlightRepository;
import com.volook.flightsManager.repositories.PromotionRepository;

import flightsManager.Flights.FlightDto;


@Service
public class FlightService {

	@Autowired
	private FlightRepository flightRepository;
	@Autowired
	private PromotionRepository promotionRepository;
	@Autowired
	private FareRepository fareRepository;
	@Autowired
	private AirportRepository airportRepository;
	
	public Flight findOne(String flightId) throws Exception {
		if (flightId == null) {
			throw new IllegalArgumentException();
		}
		Flight flight = this.flightRepository.findById(UUID.fromString(flightId)).get();
		return flight;
	}

	public Flight saveOrUpdate(FlightDto flight) throws Exception {
		if(flight==null) {
			throw new IllegalArgumentException();
		}
		Promotion promotion = this.promotionRepository.findById(UUID.fromString(flight.getPromotion())).get();
		Airport departure = this.airportRepository.findById(UUID.fromString(flight.getDeparture())).get();
		Airport destination = this.airportRepository.findById(UUID.fromString(flight.getDestination())).get();
		List<Fare> fares = new LinkedList<>();
		for(String f: flight.getFaresList()) {
			fares.add(this.fareRepository.findById(UUID.fromString(f)).get());
		}
		Flight flightToSave = new Flight();

		if(flight.getId()!=null && !flight.getId().isEmpty()) {
			flightToSave.setId(UUID.fromString(flight.getId()));
		}
		flightToSave.setName(flight.getName());
		flightToSave.setStartDateTime(new Date(flight.getStartDateTime()));
		flightToSave.setEndDateTime(new Date(flight.getEndDateTime()));
		flightToSave.setDistance(flight.getDistance());
		flightToSave.setFrequencyType(com.volook.flightsManager.utils.Frequency.valueOf(flight.getFrequencyType().toString()));
		flightToSave.setFrequency(flight.getFrequency());
		flightToSave.setSeats(flight.getSeats());
		flightToSave.setDeparture(departure);
		flightToSave.setDestination(destination);
		flightToSave.setPromotion(promotion);
		flightToSave.setFares(fares);
		Flight savedFlight = this.flightRepository.save(flightToSave);
		return savedFlight;
	}

	public void delete(String flightId) throws Exception {
		this.flightRepository.deleteById(UUID.fromString(flightId));
	}

	public List<Flight> findAll() throws Exception {
		return (List<Flight>) this.flightRepository.findAll();
	}

	public List<Flight> generateAvailableFlights(String departureAirportId,
			String destinationAirportId, Fare fare, Date departureDate) throws Exception {
		List<Flight> staticFlights = this.flightRepository.findAvailableFlights(UUID.fromString(departureAirportId),
				UUID.fromString(destinationAirportId), fare.getId(), departureDate);
		return staticFlights;
	}
}
