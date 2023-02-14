package com.toniclecb.springtests.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.toniclecb.springtests.model.Person;
import com.toniclecb.springtests.service.PersonService;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@GetMapping("/{id}")
	public Person getPerson(@PathVariable("id") Long id) {
		return personService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Person> postPerson(@RequestBody Person person) {
		Person personSaved = personService.update(person);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	            .buildAndExpand(personSaved.getId()).toUri();

	        return ResponseEntity.created(location).body(person);
	}
}
