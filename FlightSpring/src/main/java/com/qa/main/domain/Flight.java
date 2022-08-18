package com.qa.main.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flight {

	// Columns
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String startLocation;

	@Column
	private String endLocation;

	@Column
	private String airlines;

	@Column
	private LocalDate date;

	@Column(precision = 10, scale = 2)
	private BigDecimal price;

	// Constructors

	// Default constructor
	public Flight() {

	}

	// For creating
	public Flight(String startLocation, String endLocation, String airlines, LocalDate date, BigDecimal price) {
		super();
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.airlines = airlines;
		this.date = date;
		this.price = price;
	}

	// For reading
	public Flight(long id, String startLocation, String endLocation, String airlines, LocalDate date,
			BigDecimal price) {
		super();
		this.id = id;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.airlines = airlines;
		this.date = date;
		this.price = price;
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public String getAirlines() {
		return airlines;
	}

	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(airlines, date, endLocation, id, price, startLocation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(airlines, other.airlines) && Objects.equals(date, other.date)
				&& Objects.equals(endLocation, other.endLocation) && id == other.id
				&& Objects.equals(price, other.price) && Objects.equals(startLocation, other.startLocation);
	}

}
