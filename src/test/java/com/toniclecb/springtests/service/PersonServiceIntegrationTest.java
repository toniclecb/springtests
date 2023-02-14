package com.toniclecb.springtests.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.toniclecb.springtests.model.Person;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class PersonServiceIntegrationTest {

	@Autowired
	PersonService personService;

	/**
	 * This will test all PersonService and PersonRepository classes, because nothing is mocked!
	 * This will changed the database, is ideal have another database for tests and maybe use another profile!
	 */
	@Test
	public void testAddPersonHappyPath() {
		Person p = new Person();
		p.setAge(44);
		p.setGender("Male");
		p.setHeight(1.85d);
		p.setName("Patrizio Ferraresi");

		Person personSaved = personService.update(p);

		assertNotNull(personSaved);
		assertNotNull(personSaved.getId());
		assertEquals("Patrizio Ferraresi", personSaved.getName());
	}
}
