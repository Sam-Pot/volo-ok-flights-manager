package com.volook.flightsManager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.volook.flightsManager.entities.Fare;
import com.volook.flightsManager.services.FareService;

@SpringBootTest
class FlightsManagerApplicationTests {

	@Autowired
	private FareService fareService;
	
	@Test
	void testSave() {
		Fare f = new Fare();
		f.setModificationPrice(5.60f);
		f.setName("Ordinario");
		f.setPrice(4.40f);
		f.setEditable(true);
		try{
			f = fareService.saveOrUpdate(f);
		}catch(Exception e) {
			
		}
		assertThat(f.getId()).isNotNull();
	}
	
	@Test
	void testFind() {
		Fare f = new Fare();
		f.setModificationPrice(5.60f);
		f.setName("Extra");
		f.setPrice(4.40f);
		f.setEditable(true);
		try{
			f = fareService.saveOrUpdate(f);
		}catch(Exception e) {
			
		}
		assertThat(f.getId()).isNotNull();
	}

}
