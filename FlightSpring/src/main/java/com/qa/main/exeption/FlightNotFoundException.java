package com.qa.main.exeption;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Flight with that ID does not exist.")
public class FlightNotFoundException extends NoSuchElementException {

}
