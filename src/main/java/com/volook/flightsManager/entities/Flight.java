package com.volook.flightsManager.entities;

import java.util.Date;
import java.util.List;

import com.volook.flightsManager.utils.Frequency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Flight extends CustomBaseEntity {
	
	@Column()
	private String name;
	
	@Column()
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDateTime;
	
	@Column()
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDateTime;
	
	@Column(precision = 2)
	private float distance;
	
    @Enumerated(EnumType.STRING)
    private Frequency frequencyType;
    
    @Column()
    private int frequency;
    
    @Column()
    private int seats;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Fare> fares;

    @ManyToOne(fetch = FetchType.EAGER)
    private Airport departure;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Airport destination;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Promotion promotion;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public Frequency getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(Frequency frequencyType) {
		this.frequencyType = frequencyType;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public List<Fare> getFares() {
		return fares;
	}

	public void setFares(List<Fare> fares) {
		this.fares = fares;
	}

	public Airport getDeparture() {
		return departure;
	}

	public void setDeparture(Airport departure) {
		this.departure = departure;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
}
