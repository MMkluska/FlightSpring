package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.Flight;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:testschema.sql",
		"classpath:testdata.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test") // Switching to H2 for testing
public class FlightControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper; // Used for converting objects to JSON

	@Test
	public void createTest() throws Exception {

		// Create an object for posting
		Flight entry = new Flight("Paris", "Malaga", "Paris Airlines", LocalDate.of(2022, 05, 01),
				BigDecimal.valueOf(15.99));
		String entryAsJSON = mapper.writeValueAsString(entry);

		// Create an object for checking the result
		Flight result = new Flight(2L, "Paris", "Malaga", "Paris Airlines", LocalDate.of(2022, 05, 01),
				BigDecimal.valueOf(15.99));
		String resultAsJSON = mapper.writeValueAsString(result);

		mvc.perform(post("/flight/create").contentType(MediaType.APPLICATION_JSON).content(entryAsJSON))
				.andExpect(status().isCreated()).andExpect(content().json(resultAsJSON));
	}

	@Test
	public void readAllTest() throws Exception {
		// Create an object for checking the result
		List<Flight> result = new ArrayList<>();
		result.add(new Flight(1L, "London", "Madrid", "Lot", LocalDate.of(2022, 01, 01), BigDecimal.valueOf(10.99)));
		String resultAsJSON = mapper.writeValueAsString(result);

		mvc.perform(get("/flight/getAll").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(resultAsJSON));
	}

	@Test
	public void readByIdTest() throws Exception {
		Flight result = new Flight(1L, "London", "Madrid", "Lot", LocalDate.of(2022, 01, 01),
				BigDecimal.valueOf(10.99));
		String resultAsJSON = mapper.writeValueAsString(result);

		mvc.perform(get("/flight/getById/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(resultAsJSON));
	}

	@Test
	public void updateTest() throws Exception {
		// Create an object for posting
		Flight entry = new Flight("Paris", "Malaga", "Paris Airlines", LocalDate.of(2022, 05, 01),
				BigDecimal.valueOf(15.99));
		String entryAsJSON = mapper.writeValueAsString(entry);

		// Create an object for checking the result
		Flight result = new Flight(1L, "Paris", "Malaga", "Paris Airlines", LocalDate.of(2022, 05, 01),
				BigDecimal.valueOf(15.99));
		String resultAsJSON = mapper.writeValueAsString(result);

		mvc.perform(put("/flight/update/1").contentType(MediaType.APPLICATION_JSON).content(entryAsJSON))
				.andExpect(status().isNoContent()).andExpect(content().json(resultAsJSON));

	}

	@Test 
	public void deleteTest() throws Exception {
		mvc.perform(delete("/flight/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
}
