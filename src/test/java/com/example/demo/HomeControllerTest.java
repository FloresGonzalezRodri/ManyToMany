package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
@AutoConfigureMockMvc
public class HomeControllerTest
{
	@Autowired
	private MockMvc mockMvc; 
	
	@Test
	public void testHomeController()
	{
		ResultActions resultActions;
		try
		{
			resultActions = mockMvc.perform(get("/clientes"));
			resultActions.andExpect(content().contentType("application/json"));
			resultActions.andExpect(status().isOk());
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
