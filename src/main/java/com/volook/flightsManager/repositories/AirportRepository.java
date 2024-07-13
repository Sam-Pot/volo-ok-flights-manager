package com.volook.flightsManager.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.volook.flightsManager.entities.Airport;

@Repository()
public interface AirportRepository  extends CrudRepository<Airport, UUID>{

}
