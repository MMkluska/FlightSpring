package com.qa.main.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.main.domain.Flight;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {

}
