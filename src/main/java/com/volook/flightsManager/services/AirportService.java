package com.volook.flightsManager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volook.flightsManager.entities.Airport;
import com.volook.flightsManager.repositories.AirportRepository;

import flightsManager.Flights.PaginatedAirports;

@Service
public class AirportService {
	
	@Autowired
	private AirportRepository airportRepository;
	
	public List<Airport> findAll() {
		List<Airport> airports = (List<Airport>) this.airportRepository.findAll();
		return airports;
	}
}
