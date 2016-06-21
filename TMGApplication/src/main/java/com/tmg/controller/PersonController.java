package com.tmg.controller;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmg.exception.CustomException;
import com.tmg.exception.ErrorResponse;
import com.tmg.model.Person;
import com.tmg.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	PersonService personService;

	@RequestMapping("/all")
	public ResponseEntity<Hashtable<String, Person>> getAll() throws CustomException {
		if(personService.getAll() == null || personService.getAll().isEmpty()){
			throw new CustomException("No records exits ...");
		}
		return new ResponseEntity<Hashtable<String,Person>>(personService.getAll(),HttpStatus.OK);
	}

	@RequestMapping("{id}")
	public ResponseEntity<Person> getPerson(@PathVariable("id") String id) throws CustomException{
		
		if(personService.getPerson(id) == null){
			throw new CustomException("Invalid Person ID");
		}
		return new ResponseEntity<Person>(personService.getPerson(id),HttpStatus.OK);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NO_CONTENT.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
}
