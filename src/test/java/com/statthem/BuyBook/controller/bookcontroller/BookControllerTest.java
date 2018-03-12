package com.statthem.BuyBook.controller.bookcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.statthem.BuyBook.exception.NoAuthentificatedUserException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/servlet-context.xml" })
@WebAppConfiguration
public class BookControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void givenWac_whenServletContext_BookControllerNotNull() throws Exception {
		ServletContext servletContext = wac.getServletContext();

		Assert.assertNotNull(servletContext);
		Assert.assertNotNull(wac.getBean("BookController"));
	}

	@Test
	public void showBookInfo_test() throws Exception {
		this.mockMvc.perform(get("/book_catalogue/Book/{bookName}", "test"))

				.andExpect(view().name("forward:/resources/html/bookInfo.html"));
	}

	@Test
	public void noAuthentificatedUserGiven_addBookToFavourite_stausExpectationFailed() throws Exception {
		this.mockMvc.perform(get("/addToFavourite/{BookId}","11"))
		.andExpect(status().isExpectationFailed());
	}

}
