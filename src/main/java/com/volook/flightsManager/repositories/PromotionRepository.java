package com.volook.flightsManager.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.volook.flightsManager.entities.Promotion;

@Repository()
public interface PromotionRepository extends CrudRepository<Promotion, UUID> {
	
	@Query("SELECT p "
			+ "FROM Promotion p "
			+ "WHERE p.onlyForLoyalCustomer=TRUE"
	)
	public List<Promotion> findByOnlyForLoyalCustomer();
}
