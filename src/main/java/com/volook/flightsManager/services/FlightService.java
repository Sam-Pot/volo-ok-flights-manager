package com.volook.flightsManager.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volook.flightsManager.entities.Fare;
import com.volook.flightsManager.entities.Flight;
import com.volook.flightsManager.repositories.FlightRepository;
import com.volook.flightsManager.utils.GenerateFlight;

import flightsManager.Flights.AvailableFlight;

@Service
public class FlightService {

	@Autowired
	private FlightRepository flightRepository;

	public Flight findOne(String flightId) throws Exception {
		if (flightId == null) {
			throw new IllegalArgumentException();
		}
		Flight flight = this.flightRepository.findById(UUID.fromString(flightId)).get();
		return flight;
	}

	public Flight saveOrUpdate(Flight flight) throws Exception {
		if (flight == null) {
			throw new IllegalArgumentException();
		}
		Flight savedFlight = this.flightRepository.save(flight);
		return savedFlight;
	}

	public void delete(String flightId) throws Exception {
		this.flightRepository.deleteById(UUID.fromString(flightId));
	}

	public List<Flight> findAll(String query) throws Exception {
		return (List<Flight>) this.flightRepository.findAll();
	}

	public List<AvailableFlight> generateAvailableFlights(String departureMunicipalityCode,
			String destinationMunicipalityCode, Fare fare, Date departureDate) throws Exception {
		List<Flight> staticFlights = this.flightRepository.findAvailableFlights(departureMunicipalityCode,
				destinationMunicipalityCode, fare.getId().toString(), departureDate);
		List<AvailableFlight> availableFlights = new LinkedList<>();
		ExecutorService executor = Executors.newCachedThreadPool();
		for (Flight curFlight : staticFlights) {
			GenerateFlight generator = new GenerateFlight(curFlight,departureDate.getTime());
			Future<List<AvailableFlight>> generatedFlight = executor.submit(generator);
			availableFlights.addAll(generatedFlight.get());
		}
		executor.awaitTermination(2, TimeUnit.MINUTES);
		return availableFlights;
	}
}
