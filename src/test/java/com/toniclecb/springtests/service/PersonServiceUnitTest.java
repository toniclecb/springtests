package com.toniclecb.springtests.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.toniclecb.springtests.model.Person;
import com.toniclecb.springtests.model.repo.PersonRepository;

// is used to integrate the Mockito with JUnit. It allows the use of the mock objects in tests, allowing the creation and verification of interactions between the objects under test.
@ExtendWith(MockitoExtension.class)
// @SpringBootTest is used in Spring Boot applications to bootstrap an application for testing
// The webEnvironment attribute allows you to specify the type of web environment in which the test should run.
// The value WebEnvironment.NONE means that the test should run without starting a web server
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
// this work with @BeforeAll and it means that a single instance of the test class will be created and reused for all test methods within that class.
@TestInstance(Lifecycle.PER_CLASS)
public class PersonServiceUnitTest {

	@Mock
	private PersonRepository personRepository;
	
	@InjectMocks
	private PersonService personService;
	
	@BeforeAll
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	/**
	 * Here we are testing a unit, PersonService.update() method!
	 * The person returned need to be different than null,
	 * and the fields must be equals to person given in the parameter.
	 * Here we test a piece of logic too, the method  personRepository.save()
	 * needs to be called only 1 time!
	 */
	@Test
	public void testAddPersonHappyPath() {
		Person p = new Person();
		p.setAge(44);
		p.setGender("Male");
		p.setHeight(1.85d);
		p.setName("Patrizio Ferraresi");

		when(personRepository.save(any())).thenReturn(p);
		
		Person personSaved = personService.update(null);
		
		// test the return
		assertThat(personSaved, is(notNullValue()));
		assertThat(personSaved.getName(), Matchers.is(p.getName()));
		assertThat(personSaved.getAge(), Matchers.is(p.getAge()));
		// test the internal logic
		verify(personRepository, times(1)).save(Mockito.any());
	}

	/**
	 * Here we test if PersonService is throwing the exception of repository
	 * (this is the actual behavior of our PersonService).
	 * 
	 */
	@Test
    void whenSave_thenThrowException() {
		Person p = new Person();
		p.setAge(44);
		p.setGender("Male");
		p.setHeight(1.85d);
		p.setName("Patrizio Ferraresi");

        doThrow(RuntimeException.class)
                .when(personRepository).save(p);

        Exception thrown = assertThrows(RuntimeException.class, () -> personService.update(p));
        assertTrue(thrown != null);
        assertTrue(thrown instanceof RuntimeException);
    }
}
