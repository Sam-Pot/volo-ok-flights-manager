package com.volook.flightsManager.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.volook.flightsManager.entities.Fare;

@SpringBootTest
public class FareServiceTest {

	@Autowired
	private FareService fareService;
	
	
	@Test
	void testSave() {
		Fare f = new Fare();
		f.setModificationPrice(5.60f);
		f.setName("Ordinario");
		f.setPrice(4.40f);
		f.setEditable(true);
		Fare f2 = null;
		try{
			f = fareService.saveOrUpdate(f);
			f2 = fareService.saveOrUpdate(null);
		}catch(Exception e) {
			
		}
		assertThat(f.getId()).isNotNull();
		assertThat(f2).isNull();;
	}
	
	@Test
	void testFindOne() {
		Fare f = new Fare();
		f.setModificationPrice(5.60f);
		f.setName("Extra");
		f.setPrice(4.40f);
		f.setEditable(true);
		try{
			f = fareService.saveOrUpdate(f);
			Fare found = fareService.findOne(f.getId().toString());
			assertThat(found).isNotNull();
		}catch(Exception e) {
			
		}
		assertThat(f.getId()).isNotNull();
	}
	
	@Test
	void testDelete() {
		Fare f = new Fare();
		f.setModificationPrice(5.60f);
		f.setName("Ordinario");
		f.setPrice(4.40f);
		f.setEditable(true);
		try{
			f = fareService.saveOrUpdate(f);
			fareService.delete(f.getId().toString());
			
		}catch(Exception e) {
			
		}
		assertThat(f.getId()).isNotNull();	}
	
	
	@Test
	void testFindAll() {
		try{
			List<Fare> list = fareService.findAll();
			assertThat(list).isNotNull();
		}catch(Exception e) {
			
		}
	}
}
