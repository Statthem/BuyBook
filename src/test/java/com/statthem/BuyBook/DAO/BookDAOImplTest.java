package com.statthem.BuyBook.DAO;

import javax.servlet.ServletContext;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/servlet-context.xml"})
@WebAppConfiguration
public class BookDAOImplTest {
	
	@Autowired
	@Qualifier(value = "hibernate4AnnotatedSessionFactory")
	SessionFactory sessionFactory;
	
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
		Assert.assertNotNull(wac.getBean("BookDAOImpl"));
		Assert.assertNotNull(wac.getBean("hibernate4AnnotatedSessionFactory"));
	}
	
	
	@Transactional
	@Test
	public void givenSessionFactory_GetCurrentSessionNotNull() {
		Assert.assertNotNull("problem with db connection",sessionFactory.getCurrentSession());
	}

}
