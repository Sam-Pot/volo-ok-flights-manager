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
			+ "WHERE f.departure.municipalityCode = :departureMunicipalityCode "
			+ "AND f.destination.municipalityCode = :destinationMunicipalityCode "
			+ "AND fa.id = :fareId "
			+ "AND :departureDate BETWEEN f.startDateTime AND f.endDateTime "
			)
	public List<Flight> findAvailableFlights(
			@Param("departureMunicipalityCode") String departureMunicipalityCode, 
			@Param("destinationMunicipalityCode") String destinationMunicipalityCode,
			@Param("fareId") String fareId,
			@Param("departureDate") Date departureDate
	);

}
