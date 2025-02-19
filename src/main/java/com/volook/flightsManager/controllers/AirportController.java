package com.volook.flightsManager.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.volook.flightsManager.converters.AirportToAirportDto;
import com.volook.flightsManager.entities.Airport;
import com.volook.flightsManager.services.AirportService;

import flightsManager.AirportServiceGrpc;
import flightsManager.Flights;
import flightsManager.Flights.PaginatedAirports;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AirportController extends AirportServiceGrpc.AirportServiceImplBase{
	
	@Autowired
	private AirportService airportService;
	@Autowired
	private AirportToAirportDto airportToAirportDto;
	
	@Override
	public void findAll(flightsManager.Flights.IdDto request,StreamObserver<PaginatedAirports> responseObserver) {
		try {
			List<Airport> airportsList = this.airportService.findAll();
			List<flightsManager.Flights.Airport> airports = new LinkedList<>();
			for(Airport airport: airportsList) {
				airports.add(airportToAirportDto.convert(airport));
			}
			PaginatedAirports response = PaginatedAirports.newBuilder()
					.addAllAirports(airports)
					.build();
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}
	
	@Override
	public void findOne(flightsManager.Flights.IdDto request, StreamObserver<flightsManager.Flights.Airport> responseObserver) {
		try {
			Airport airportFound = this.airportService.findOne(request.getId());
			responseObserver.onNext(airportToAirportDto.convert(airportFound));
			responseObserver.onCompleted();
		}catch(Exception e) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}  
	}
	
}
