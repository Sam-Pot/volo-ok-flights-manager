package com.volook.flightsManager.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.volook.flightsManager.entities.Flight;

@Repository()
public interface FlightRepository extends CrudRepository<Flight, UUID> {

	@Query(
			"SELECT f "
			+ "FROM Flight f JOIN f.fares fa "
			+ "WHERE f.departure.id = :departureId "
			+ "AND f.destination.id = :destinationId "
			+ "AND fa.id = :fareId "
			+ "AND :departureDate BETWEEN f.startDateTime AND f.endDateTime "
			)
	public List<Flight> findAvailableFlights(
			@Param("departureId") UUID departureId, 
			@Param("destinationId") UUID destinationId,
			@Param("fareId") UUID fareId,
			@Param("departureDate") Date departureDate
	);

}
