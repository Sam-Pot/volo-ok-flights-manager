package com.volook.flightsManager.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volook.flightsManager.entities.Airport;
import com.volook.flightsManager.repositories.AirportRepository;

@Service
public class AirportService {
	
	@Autowired
	private AirportRepository airportRepository;
	
	public List<Airport> findAll() {
		List<Airport> airports = (List<Airport>) this.airportRepository.findAll();
		return airports;
	}
	
	public Airport findOne(String id) {
		Optional<Airport> airportFound = this.airportRepository.findById(id);
		if(airportFound!=null) {
			return airportFound.get();
		}
		return null;
	}
}
