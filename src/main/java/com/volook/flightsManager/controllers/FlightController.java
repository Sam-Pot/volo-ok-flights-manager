package com.volook.flightsManager.controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.volook.flightsManager.converters.FareDtoToFare;
import com.volook.flightsManager.converters.FlightDtoToFlight;
import com.volook.flightsManager.converters.FlightToFlightDto;
import com.volook.flightsManager.services.FlightService;

import flightsManager.FlightServiceGrpc;
import flightsManager.Flights.AvailableFlights;
import flightsManager.Flights.Flight;
import flightsManager.Flights.FlightDto;
import flightsManager.Flights.IdDto;
import flightsManager.Flights.PaginatedFlights;
import flightsManager.Flights.QueryDto;
import flightsManager.Flights.SearchFlightsDto;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class FlightController extends FlightServiceGrpc.FlightServiceImplBase {
	@Autowired
	private FlightService flightService;
	@Autowired
	private FlightDtoToFlight dtoToEntity;
	@Autowired
	private FlightToFlightDto entityToDto;
	@Autowired
	private FareDtoToFare fareDtoToFare;

	@Override
	public void findOne(IdDto idDto, StreamObserver<Flight> responseObserver) {
		if (idDto == null) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
		String flightId = idDto.getId();
		try {
			responseObserver.onNext(entityToDto.convert(this.flightService.findOne(flightId)));
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}

	public void saveOrUpdate(FlightDto flight, StreamObserver<Flight> responseObserver) {
		if (flight == null) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
		try {
			responseObserver.onNext(entityToDto.convert(this.flightService.saveOrUpdate(flight)));
			responseObserver.onCompleted();
		} catch (Exception e) {
			e.printStackTrace();
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}

	public void delete(IdDto idDto, StreamObserver<Flight> responseObserver) {
		if (idDto == null) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
		try {
			Flight flightToDelete = entityToDto.convert(this.flightService.findOne(idDto.getId()));
			this.flightService.delete(idDto.getId());
			responseObserver
					.onNext(entityToDto.convert(dtoToEntity.convert(flightToDelete)));
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}

	public void find(QueryDto request, StreamObserver<PaginatedFlights> responseObserver) {
		try {
			List<com.volook.flightsManager.entities.Flight> flights = this.flightService.findAll();
			List<Flight> response = new LinkedList<>();
			for (com.volook.flightsManager.entities.Flight elem : flights) {
				response.add(entityToDto.convert(elem));
			}
			PaginatedFlights paginatedFlights = PaginatedFlights.newBuilder().setElementsNumber(flights.size())
					.addAllFlights(response).build();
			responseObserver.onNext(paginatedFlights);
			responseObserver.onCompleted();
		} catch (Exception e) {
			e.printStackTrace();
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}
	
	public void generateFlights(SearchFlightsDto request, StreamObserver<AvailableFlights> responseObserver) {
		if(request==null) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
		try {
			List<com.volook.flightsManager.entities.Flight> flights = this.flightService.generateAvailableFlights(request.getDepartureAirportId(), 
					request.getDestinationAirportId(),fareDtoToFare.convert(request.getFare()), new Date(request.getDepartureDate()));
			List<Flight> availableFlights = new LinkedList<>();
			for(com.volook.flightsManager.entities.Flight f: flights) {
				availableFlights.add(entityToDto.convert(f));
			}
			responseObserver.onNext(AvailableFlights.newBuilder().addAllAvailableFlight(availableFlights).build());
			responseObserver.onCompleted();
		}catch(Exception e) {
			e.printStackTrace();
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}
	
}