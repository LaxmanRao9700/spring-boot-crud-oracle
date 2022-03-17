package com.springcurd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = SpringcurdApplication.class)
@WebAppConfiguration
public abstract class AbstarctTest {
	
	
	public MockMvc mockMvc;
	
	@Autowired
	public WebApplicationContext webApplicationContext;

	public void setUp() {
		mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	public String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	public <T> T mapFormJson(String json, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json,clazz);
	}
}
