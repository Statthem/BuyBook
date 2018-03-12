package com.statthem.BuyBook.controller.usercontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.statthem.BuyBook.model.Book;
import com.statthem.BuyBook.model.User;

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
			.andExpect(view().name("RegistrationPage"))
			.andReturn();
		 
		 Assert.assertNotNull(mvcResult.getRequest().getAttribute("user"));
	}
	

	@Test(expected=org.springframework.web.util.NestedServletException.class)
	public void givenValidParameters_RegisterUserAccaunt_test_userAttributeNotNull() throws Exception {
		User user = new User();
		user.setUserName("test");
		user.setUserEmail("test5@ukr.net");
		user.setUserPassword("test");
		user.setMatchingPassword("test");
		
		
		
		 MvcResult mvcResult = this.mockMvc.perform(post("/user/registration").flashAttr("user", user))
				 .andDo(print())
			     .andExpect(view().name("SuccessRegisterPage")).andReturn();
		 
		 Assert.assertNotNull(mvcResult.getModelAndView().getModel().get("user"));
		
	}
	
	@Test
	public void givenInValidParameters_RegisterUserAccaunt_test_errorsListNotNull() throws Exception {
		User user = new User();
		user.setUserName("test");
		user.setUserEmail("test@ukr.net");
		user.setUserPassword("test");
		user.setMatchingPassword("fake");
		
		
		
		 MvcResult mvcResult = this.mockMvc.perform(post("/user/registration").flashAttr("user", user))
				 .andDo(print())
			     .andExpect(view().name("RegistrationPage")).andReturn();
		 List<String> errors = (List<String>) mvcResult.getModelAndView().getModel().get("errorsList");
		 Assert.assertNotNull(errors);
		 Assert.assertTrue(!errors.isEmpty());
		
	}
	
}
