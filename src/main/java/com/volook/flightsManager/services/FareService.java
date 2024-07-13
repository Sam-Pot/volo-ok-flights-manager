package com.volook.flightsManager.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volook.flightsManager.entities.Fare;
import com.volook.flightsManager.repositories.FareRepository;

@Service
public class FareService {
	
	@Autowired
	private FareRepository fareRepository;
	
	public Fare findOne(String fareId) throws Exception{
		if(fareId==null) {
			throw new IllegalArgumentException();
		}
		Fare fare = this.fareRepository.findById(UUID.fromString(fareId)).get();
		return fare;
	}
	
	public Fare saveOrUpdate(Fare fare) throws Exception{
		if(fare==null) {
			throw new IllegalArgumentException();
		}
		Fare savedFare = this.fareRepository.save(fare);
		return savedFare;
	}
	
	public void delete(String fareId) throws Exception{
		this.fareRepository.deleteById(UUID.fromString(fareId));
	}
	
	public List<Fare> findAll(String query) throws Exception{
		return (List<Fare>) this.fareRepository.findAll();
	}
}
