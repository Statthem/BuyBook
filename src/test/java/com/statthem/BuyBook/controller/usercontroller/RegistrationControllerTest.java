package com.statthem.BuyBook.controller.usercontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/servlet-context.xml"})
@WebAppConfiguration
public class RegistrationControllerTest {
	

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void givenWac_whenServletContext_RegistrationControlleNotNull() throws Exception {
		ServletContext servletContext = wac.getServletContext();

		Assert.assertNotNull(servletContext);
		 Assert.assertNotNull(wac.getBean("RegistrationController"));
	}
	
	@Test
	public void getRegistrationPage_test() throws Exception {
		 MvcResult mvcResult = this.mockMvc.perform(get("/registration"))
			.andExpect(view().name("RegistrationPage")).andReturn();
		 
		 Assert.assertNotNull(mvcResult.getRequest().getAttribute("user"));
	}
	
	//Not finished
//	@Test
//	public void RegisterUserAccaunt_test() throws Exception {
//		 MvcResult mvcResult = this.mockMvc.perform(get("/user/registration"))
//			.andExpect(view().name("RegistrationPage")).andReturn();
//		 
//		
//	}
}
