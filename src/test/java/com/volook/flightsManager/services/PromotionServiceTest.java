package com.volook.flightsManager.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.volook.flightsManager.entities.Fare;
import com.volook.flightsManager.entities.Promotion;

@SpringBootTest
public class PromotionServiceTest {
	
	@Autowired
	private PromotionService promotionService;
	@Test
	void testSave() {
		Promotion p = new Promotion();
		p.setName("Promozione");
		p.setOnlyForLoyalCustomer(true);
		p.setDiscountPercentage(2.3f);
		p.setStartDate(new Date());
		p.setEndDate(new Date());
		Promotion p2 = null;
		try{
			p = promotionService.saveOrUpdate(p);
			p2 = promotionService.saveOrUpdate(null);
		}catch(Exception e) {
			
		}
		assertThat(p.getId()).isNotNull();
		assertThat(p2).isNull();;
	}
	
	@Test
	void testFindOne() {
		Promotion p = new Promotion();
		p.setName("Promozione");
		p.setOnlyForLoyalCustomer(true);
		p.setDiscountPercentage(2.3f);
		p.setStartDate(new Date());
		p.setEndDate(new Date());
		Promotion p2 = null;
		try{
			p = promotionService.saveOrUpdate(p);
			Promotion found = promotionService.findOne(p.getId().toString());
			assertThat(found).isNotNull();
		}catch(Exception e) {
			
		}
	}
	
	@Test
	void testDelete() {
		Promotion p = new Promotion();
		p.setName("Promozione");
		p.setOnlyForLoyalCustomer(true);
		p.setDiscountPercentage(2.3f);
		p.setStartDate(new Date());
		p.setEndDate(new Date());
		Promotion p2 = null;
		try{
			p = promotionService.saveOrUpdate(p);
			promotionService.delete(p.getId().toString());
			
		}catch(Exception e) {
			
		}
		assertThat(p.getId()).isNotNull();	
	}
	
	
	@Test
	void testFindAll() {
		Promotion p = new Promotion();
		p.setName("Promozione");
		p.setOnlyForLoyalCustomer(true);
		p.setDiscountPercentage(2.3f);
		p.setStartDate(new Date());
		p.setEndDate(new Date());

		Promotion p2 = new Promotion();
		p2.setName("Promozione2");
		p2.setOnlyForLoyalCustomer(false);
		p2.setDiscountPercentage(2.3f);
		p2.setStartDate(new Date());
		p2.setEndDate(new Date());
		try{
			List<Promotion> list = promotionService.findAll("");
			assertThat(list).isNotNull();
		}catch(Exception e) {
			
		}
	}
}
