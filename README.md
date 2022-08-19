# Flight - Spring Boot Project

  A project that connects a front-end HTML page to a back-end MySQL database, using Spring Boot framework to create custom API.  

## User stories and Sprints 

* [Jira](https://mmkluska.atlassian.net/jira/software/projects/FLIG/boards/5) - Agile Project Management

## Installing

1. Fork this repository
2. Clone forked repository to your local device
3. Open a comand prompt/git bash 
4. Navigate to project location
5. Type: java -jar FlightSpring-0.0.1-SNAPSHOT.jar

### Prerequisites

  Database - [MySQL Server 5.7+](https://www.mysql.com/products/workbench/)<br>
  Back-end Programming Language - [Java](https://www.java.com/) <br>
  Build Tool - [Maven](https://maven.apache.org/) <br>
  Unit Testing - [JUnit](https://junit.org/junit4/) <br>

### Getting Started

1. Launch a project in comand prompt/git bash or in your local IDE 
2. Open your local browser
3. type: http://localhost:8080/index.html 

## Running the tests

1. In your local IDE right click on project
2. Select JUnit 4
3. Run tests
4. Select coverage to check % of testing

### Unit Tests 

A unit is the smallest whole increment, from which this testing gets its name. It is by far one of the most important tests and it tests a small amount of code, usually a single method, to see if it returns the expected output.

```
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
```

### Integration Testing

Integration testing is the phase in software testing in which individual software modules are combined and tested as a group. Integration testing is conducted to evaluate the compliance of a system or component with specified functional requirements.

```
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

		mvc.perform(post("/flight/create")
				.contentType(MediaType.APPLICATION_JSON).content(entryAsJSON))
				.andExpect(status().isCreated()).andExpect(content().json(resultAsJSON));
	}
```

## Built With

* [Jira](https://mmkluska.atlassian.net/jira/software/projects/IMS/boards/2) - Agile Project Management
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [GitHub](https://github.com/) for versioning.

## Authors

* **Mateusz Kluska** - [MMkluska](https://github.com/MMkluska/FlightSpring)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Many thanks to tutors at QA Academy
* Google search engine 
* Stack Overflow
