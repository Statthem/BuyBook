package com.statthem.BuyBook.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.statthem.BuyBook.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/servlet-context.xml" })
@WebAppConfiguration
public class MainControllerIntegrationTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void givenWac_whenServletContext_MainControllerNotNull() {
		ServletContext servletContext = wac.getServletContext();

		Assert.assertNotNull(servletContext);
		Assert.assertTrue(servletContext instanceof MockServletContext);

		Assert.assertNotNull(wac.getBean("MainController"));
	}

	@Test
	public void givenMainPageURI_whenMockMVC_thenReturnsMainPageJSPViewName() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/book_catalogue"))

				.andExpect(view().name("MainPage")).andReturn();

		Set<Book> books = (Set<Book>) mvcResult.getModelAndView().getModel().get("allBooks");
		Assert.assertNotNull(books);
		Assert.assertTrue(!books.isEmpty());
		Assert.assertNotNull(mvcResult.getModelAndView().getModel().get("genreList"));
	}

	@Test
	public void givenOrderByParameterer_OrderByTest_sortedBooksNotNull_thenRedirects()
			throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/book_catalogue/{OrderBy}", "OrderByName"))
				.andExpect(view().name("redirect:/book_catalogue"))
				.andReturn();
		
		List<Book> books = (List<Book>) mvcResult.getRequest().getSession().getAttribute("sortedBooks");
		Assert.assertNotNull(books);
		Assert.assertTrue(!books.isEmpty());
	}

	@Test
	public void givenFilterByParameterer_FilterByTest_filteredBooksNotNull_thenRedirects()
			throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/book_catalogue/filter/{filterBy}", "filterByDrama"))
				.andExpect(view().name("redirect:/book_catalogue"))
				.andReturn();
		
		List<Book> books = (List<Book>) mvcResult.getRequest().getSession().getAttribute("filteredBooks");
		Assert.assertNotNull(books);
		Assert.assertTrue(!books.isEmpty());

		Assert.assertEquals("Drama", mvcResult.getRequest().getSession().getAttribute("filteredBy"));

	}

	@Test
	public void givenNoneFilterByParameterer_FilterByTest_sortingAndFilteringAtributesRemoved_thenRedirects()
			throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/book_catalogue/filter/{filterBy}", "filterByNone"))
				.andExpect(view().name("redirect:/book_catalogue"))
				.andReturn();

		Assert.assertNull(mvcResult.getRequest().getSession().getAttribute("filteredBooks"));
		Assert.assertNull(mvcResult.getRequest().getSession().getAttribute("sortedBooks"));
		Assert.assertNull(mvcResult.getRequest().getSession().getAttribute("filteredBy"));
		Assert.assertNull(mvcResult.getRequest().getSession().getAttribute("OrderedBy"));

	}

	@Test
	public void givenSearchParameter_searchBookTest_searchedBookNotNull_thenReturnsMainPageJSPViewName() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(post("/book_catalogue/search").param("bookName", "123"))
				.andExpect(view().name("MainPage"))
				.andReturn();

		List<Book> book = (List<Book>) mvcResult.getRequest().getSession().getAttribute("searchedBook");
		Assert.assertNotNull(book);
		Assert.assertTrue(!book.isEmpty());

	}

}
