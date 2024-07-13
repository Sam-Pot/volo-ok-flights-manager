package com.volook.flightsManager.repositories;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.volook.flightsManager.entities.Fare;

@Repository()
public interface FareRepository extends CrudRepository<Fare, UUID> {
	
}
