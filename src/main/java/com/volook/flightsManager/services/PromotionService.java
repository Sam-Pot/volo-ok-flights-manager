package com.volook.flightsManager.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volook.flightsManager.entities.Promotion;
import com.volook.flightsManager.repositories.PromotionRepository;

@Service
public class PromotionService {
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	public Promotion findOne(String promotionId) throws Exception{
		if(promotionId==null) {
			throw new IllegalArgumentException();
		}
		Promotion promotion = this.promotionRepository.findById(UUID.fromString(promotionId)).get();
		return promotion;
	}
	
	public Promotion saveOrUpdate(Promotion promotion) throws Exception{
		if(promotion==null) {
			throw new IllegalArgumentException();
		}
		Promotion savedPromotion = this.promotionRepository.save(promotion);
		return savedPromotion;
	}
	
	public void delete(String promotionId) throws Exception{
		this.promotionRepository.deleteById(UUID.fromString(promotionId));
	}
	
	public List<Promotion> findAll(String query) throws Exception{
		return (List<Promotion>) this.promotionRepository.findAll();
	}
	
	public List<Promotion> getLoyaltyCustomerPromotions(String query) throws Exception{
		List<Promotion> promotions = this.promotionRepository.findByOnlyForLoyalCustomer();
		return promotions;
	}
	
}
