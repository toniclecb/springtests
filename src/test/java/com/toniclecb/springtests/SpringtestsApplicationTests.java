package com.toniclecb.springtests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.toniclecb.springtests.controller.PersonController;

/**
 * The @SpringBootTest annotation tells Spring Boot to look for a main configuration class 
 * (one with @SpringBootApplication, for instance) and use that to start a Spring application context.
 *
 */
@SpringBootTest
class SpringtestsApplicationTests {

	// Spring interprets the @Autowired annotation, and the controller is injected before the test methods are run.
	@Autowired
	PersonController personController;
	
	/**
	 * Simple sanity check test that will fail if the application context cannot start.
	 */
	@Test
	void contextLoads() {
		// To convince that the context is creating the controller, you could add an assertion like that
		assertThat(personController).isNotNull();
	}

}
