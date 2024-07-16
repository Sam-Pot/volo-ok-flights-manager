package com.volook.flightsManager.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.volook.flightsManager.converters.FareDtoToFare;
import com.volook.flightsManager.converters.FareToFareDto;
import com.volook.flightsManager.services.FareService;

import flightsManager.FareServiceGrpc;
import flightsManager.Flights.Fare;
import flightsManager.Flights.IdDto;
import flightsManager.Flights.PaginatedFares;
import flightsManager.Flights.QueryDto;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class FareController extends FareServiceGrpc.FareServiceImplBase {

	@Autowired
	private FareService fareService;
	@Autowired
	private FareDtoToFare dtoToEntity;
	@Autowired
	private FareToFareDto entityToDto;

	@Override
	public void findOne(IdDto idDto, StreamObserver<Fare> responseObserver) {
		if (idDto == null) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
		String fareId = idDto.getId();
		try {
			responseObserver.onNext(entityToDto.convert(this.fareService.findOne(fareId)));
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}
	@Override
	public void saveOrUpdate(Fare fare, StreamObserver<Fare> responseObserver) {
		if (fare == null) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
		try {
			responseObserver.onNext(entityToDto.convert(this.fareService.saveOrUpdate(dtoToEntity.convert(fare))));
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}
	
	@Override
	public void delete(IdDto idDto, StreamObserver<Fare> responseObserver) {
		if (idDto == null) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
		try {
			Fare fareToDelete = entityToDto.convert(this.fareService.findOne(idDto.getId()));
			this.fareService.delete(idDto.getId());
			responseObserver
					.onNext(entityToDto.convert(dtoToEntity.convert(fareToDelete)));
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}
	
	@Override
	public void findAll(QueryDto request, StreamObserver<PaginatedFares> responseObserver) {
		 try {
			 List<com.volook.flightsManager.entities.Fare> fares = this.fareService.findAll();
			 List<Fare> response = new LinkedList<>();
			 for(com.volook.flightsManager.entities.Fare elem: fares) {
				 response.add(entityToDto.convert(elem));
			 }
			 PaginatedFares paginatedFares = PaginatedFares.newBuilder()
					 .setElementsNumber(fares.size())
					 .addAllFares(response)
					 .build();
			 responseObserver.onNext(paginatedFares);
			 responseObserver.onCompleted();
		 }catch(Exception e) {
				responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		 }
	 }

}
