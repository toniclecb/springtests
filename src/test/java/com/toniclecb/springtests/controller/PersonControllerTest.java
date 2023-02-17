package com.toniclecb.springtests.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * start the application and listen for a connection (as it would do in production) and then send an HTTP request and assert the response.
 * 
 * use of webEnvironment=RANDOM_PORT to start the server with a random port (useful to avoid conflicts in test environments) 
 * @throws Exception
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/person/check",
				String.class)).contains("UP");
	}
}
