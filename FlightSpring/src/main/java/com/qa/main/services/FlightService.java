package com.qa.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.main.domain.Flight;
import com.qa.main.exeption.FlightNotFoundException;
import com.qa.main.repos.FlightRepo;

@Service
public class FlightService {

	private FlightRepo repo;

	public FlightService(FlightRepo repo) {
		this.repo = repo;
	}

	public Flight create(Flight entry) {
		return repo.saveAndFlush(entry);
	}

	public List<Flight> getAll() {
		return repo.findAll();
	}

	public Flight getById(long id) {
		return repo.findById(id).orElseThrow(FlightNotFoundException::new);
	}

	public Flight updateById(long id, Flight entry) {
		// We get the existing entry
		Flight existing = repo.findById(id).get();

		// Update the existing entry,
		existing.setStartLocation(entry.getStartLocation());
		existing.setEndLocation(entry.getEndLocation());
		existing.setAirlines(entry.getAirlines());
		existing.setDate(entry.getDate());
		existing.setPrice(entry.getPrice());

		// Save the update back into the DB(ID is the same)
		return repo.saveAndFlush(existing);
	}

	public Boolean deleteById(long id) {
		repo.deleteById(id);

		return !repo.existsById(id);
	}

}
