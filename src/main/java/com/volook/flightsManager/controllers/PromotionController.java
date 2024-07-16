package com.volook.flightsManager.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.volook.flightsManager.converters.PromotionDtoToPromotion;
import com.volook.flightsManager.converters.PromotionToPromotionDto;
import com.volook.flightsManager.services.PromotionService;

import flightsManager.PromotionServiceGrpc;
import flightsManager.Flights.Promotion;
import flightsManager.Flights.IdDto;
import flightsManager.Flights.PaginatedPromotions;
import flightsManager.Flights.QueryDto;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PromotionController extends PromotionServiceGrpc.PromotionServiceImplBase{
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private PromotionDtoToPromotion dtoToEntity;
	@Autowired
	private PromotionToPromotionDto entityToDto;

	@Override
	public void findOne(IdDto idDto, StreamObserver<Promotion> responseObserver) {
		if (idDto == null) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
		String promotionId = idDto.getId();
		try {
			responseObserver.onNext(entityToDto.convert(this.promotionService.findOne(promotionId)));
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}
	
	@Override
	public void saveOrUpdate(Promotion promotion, StreamObserver<Promotion> responseObserver) {
		if (promotion == null) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
		try {
			responseObserver.onNext(entityToDto.convert(this.promotionService.saveOrUpdate(dtoToEntity.convert(promotion))));
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}

	@Override
	public void delete(IdDto idDto, StreamObserver<Promotion> responseObserver) {
		if (idDto == null) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
		try {
			Promotion promotionToDelete = entityToDto.convert(this.promotionService.findOne(idDto.getId()));
			this.promotionService.delete(idDto.getId());
			responseObserver
					.onNext(entityToDto.convert(dtoToEntity.convert(promotionToDelete)));
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		}
	}

	@Override
	public void findAll(QueryDto request, StreamObserver<PaginatedPromotions> responseObserver) {
		 try {
			 List<com.volook.flightsManager.entities.Promotion> promotions = this.promotionService.findAll(null);
			 List<Promotion> response = new LinkedList<>();
			 for(com.volook.flightsManager.entities.Promotion elem: promotions) {
				 response.add(entityToDto.convert(elem));
			 }
			 PaginatedPromotions paginatedPromotions = PaginatedPromotions.newBuilder()
					 .setElementsNumber(promotions.size())
					 .addAllPromotions(response)
					 .build();
			 responseObserver.onNext(paginatedPromotions);
			 responseObserver.onCompleted();
		 }catch(Exception e) {
				responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		 }
	 }
	
	@Override
	public void getLoyaltyCustomerPromotions(QueryDto query, StreamObserver<PaginatedPromotions> responseObserver) {
		 try {
			 List<com.volook.flightsManager.entities.Promotion> promotions = this.promotionService.getLoyaltyCustomerPromotions(query.toString());
			 List<Promotion> response = new LinkedList<>();
			 for(com.volook.flightsManager.entities.Promotion elem: promotions) {
				 response.add(entityToDto.convert(elem));
			 }
			 PaginatedPromotions paginatedPromotions = PaginatedPromotions.newBuilder()
					 .setElementsNumber(promotions.size())
					 .addAllPromotions(response)
					 .build();
			 responseObserver.onNext(paginatedPromotions);
			 responseObserver.onCompleted();
		 }catch(Exception e) {
				responseObserver.onError(new StatusRuntimeException(Status.INVALID_ARGUMENT));
		 }
	 }
}
