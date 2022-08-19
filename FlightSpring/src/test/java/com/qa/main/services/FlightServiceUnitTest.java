package com.qa.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.main.domain.Flight;
import com.qa.main.repos.FlightRepo;

@SpringBootTest
public class FlightServiceUnitTest {

	@Autowired
	private FlightService service;

	@MockBean
	private FlightRepo repo;

	@Test
	public void createTest() {
		// Create and object for the entry
		Flight entry = new Flight("Paris", "Malaga", "Paris Airlines", LocalDate.of(2022, 05, 01),
				BigDecimal.valueOf(15.99));

		// Create an object for the result
		Flight result = new Flight(2L, "Paris", "Malaga", "Paris Airlines", LocalDate.of(2022, 05, 01),
				BigDecimal.valueOf(15.99));

		Mockito.when(repo.saveAndFlush(entry)).thenReturn(result);

		assertEquals(result, service.create(entry));
	}

	@Test
	public void getAllTest() {
		// Create an object for checking the result
		List<Flight> result = new ArrayList<>();
		result.add(new Flight(1L, "London", "Madrid", "Lot", LocalDate.of(2022, 01, 01), BigDecimal.valueOf(10.99)));

		Mockito.when(repo.findAll()).thenReturn(result);

		assertEquals(result, service.getAll());

		;
	}

	@Test
	public void getByIdTest() {

		long id = 1L;

		// Create and object for checking the result
		Flight result = new Flight(1L, "London", "Madrid", "Lot", LocalDate.of(2022, 01, 01),
				BigDecimal.valueOf(10.99));
		Mockito.when(repo.findById(id)).thenReturn(Optional.of(result));

		assertEquals(result, service.getById(id));

	}

	@Test
	public void updateByIdTest() {

		long id = 1L;

		Flight entry = new Flight("Paris", "Malaga", "Paris Airlines", LocalDate.of(2022, 05, 01),
				BigDecimal.valueOf(15.99));
		Flight existing = new Flight(1L, "London", "Madrid", "Lot", LocalDate.of(2022, 01, 01),
				BigDecimal.valueOf(10.99));
		Mockito.when(repo.findById(id)).thenReturn(Optional.of(existing));

		Flight update = new Flight(1L, "Paris", "Malaga", "Paris Airlines", LocalDate.of(2022, 05, 01),
				BigDecimal.valueOf(15.99));

		Mockito.when(repo.saveAndFlush(update)).thenReturn(update);

		assertEquals(update, service.updateById(id, entry));

	}
	
	@Test
	public void deleteTest() {
		
		long id = 1L;
					
		Mockito.when(service.deleteById(id)).thenReturn(true);
		
		assertEquals(false, service.deleteById(id));
	}

}
