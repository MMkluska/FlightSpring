package com.qa.main.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.domain.Flight;
import com.qa.main.services.FlightService;

@RestController
@CrossOrigin
@RequestMapping("/flight")
public class FlightController {
	
	private FlightService service;
	
	public FlightController(FlightService service) {
		this.service = service;
	}

	// POST request - CREATE
	@PostMapping("/create")
	public ResponseEntity<Flight> create(@RequestBody Flight entry) {
		return new ResponseEntity<Flight>(service.create(entry), HttpStatus.CREATED);
	}

	// GET request - READ
	@GetMapping("/getAll")
	public ResponseEntity<List<Flight>> getAll() {
		return new ResponseEntity<List<Flight>>(service.getAll(), HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Flight> getById(@PathVariable long id) {
		return new ResponseEntity<Flight>(service.getById(id), HttpStatus.OK);
	}

	// PUT request - UPDATE
	@PutMapping("/update/{id}")
	public ResponseEntity<Flight> updateById(@PathVariable long id, @RequestBody Flight entry) {
		return new ResponseEntity<Flight>(service.updateById(id, entry), HttpStatus.NO_CONTENT);
	}

	// DELETE request - DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable long id) {
		return new ResponseEntity<Boolean>(service.deleteById(id), HttpStatus.NO_CONTENT);
	}

}
