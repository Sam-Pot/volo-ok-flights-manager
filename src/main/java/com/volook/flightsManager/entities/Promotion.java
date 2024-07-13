package com.volook.flightsManager.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
public class Promotion extends CustomBaseEntity{
	
	@Column()
	private String name;
	
	@Column()
	private Date startDate;
	
	@Column()
	private Date endDate;
	
	@Column(precision = 2)
	private float discountPercentage;
	
	@Column()
	private boolean onlyForLoyalCustomer;

	public boolean isOnlyForLoyalCustomer() {
		return onlyForLoyalCustomer;
	}

	public void setOnlyForLoyalCustomer(boolean onlyForLoyalCustomer) {
		this.onlyForLoyalCustomer = onlyForLoyalCustomer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
}
