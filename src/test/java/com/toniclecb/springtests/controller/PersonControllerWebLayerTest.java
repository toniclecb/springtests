package com.toniclecb.springtests.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.toniclecb.springtests.service.PersonService;

/**
 *  we can force the tests to only the web layer by using @WebMvcTest
 *  in this test, Spring Boot instantiates only the web layer rather than the whole context.
 *  
 */
@WebMvcTest
public class PersonControllerWebLayerTest {
	@Autowired
	private MockMvc mockMvc;
	
	/**
	 * @MockBean it's necessary in PersonService because PersonController has an injection of this bean!
	 * then we mock it here!
	 */
	@MockBean
	private PersonService personService;

	@Test
	public void checkShouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/api/v1/person/check")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("UP")));
	}
}
