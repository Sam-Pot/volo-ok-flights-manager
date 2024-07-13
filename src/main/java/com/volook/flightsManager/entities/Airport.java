package com.volook.flightsManager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Airport extends CustomBaseEntity{
	
	@Column()
	private String name;
	
	@Column()
	private String municipality;
	
	@Column()
	private String municipalityCode;
	
	@Column()
	private String nationalCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getMunicipalityCode() {
		return municipalityCode;
	}

	public void setMunicipalityCode(String municipalityCode) {
		this.municipalityCode = municipalityCode;
	}

	public String getNationalCode() {
		return nationalCode;
	}

	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}
}
