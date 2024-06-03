package com.volok.flightsManager.model;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

public abstract class GeneralEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
	
	@CreatedDate()
	private Date createdAt;
	
	@UpdateTimestamp()
	private Date updatedAt;
	
	@Version
	private int version;
}
