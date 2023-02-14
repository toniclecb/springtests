package com.toniclecb.springtests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toniclecb.springtests.model.Person;
import com.toniclecb.springtests.model.repo.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;
	
	public Person findById(Long id) {
		return personRepository.findById(id).orElseThrow();
	}

	public Person update(Person person) {
		return personRepository.save(person);
	}
}
