package com.volook.flightsManager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Fare extends CustomBaseEntity{
	
	@Column()
	private String name;
	
	@Column(precision = 2)
	private float price;
	
	@Column()
	private boolean editable;
	
	@Column(precision = 2)
	private float modificationPrice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public float getModificationPrice() {
		return modificationPrice;
	}

	public void setModificationPrice(float modificationPrice) {
		this.modificationPrice = modificationPrice;
	}
}
