package com.statthem.BuyBook.controller.bookcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/servlet-context.xml" })
@WebAppConfiguration
public class BookUploadControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void givenWac_whenServletContext_BookUploadControllerNotNull() throws Exception {
		ServletContext servletContext = wac.getServletContext();

		Assert.assertNotNull(servletContext);
		Assert.assertNotNull(wac.getBean("BookUploadController"));
	}

	@Test
	public void uploadURIProvided_getUploadPage_modelAttributesNotNull_viewNameUploadPage() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/upload")).andExpect(view().name("UploadPage")).andReturn();

		Assert.assertNotNull(mvcResult.getRequest().getAttribute("Book"));
		Assert.assertNotNull(mvcResult.getRequest().getAttribute("genreList"));
	}

	@Test
	public void uploadBookURIProvided_uploadBook_viewNameUploadPage() throws Exception {
		Book book = new Book();
		book.setBookName("test");
		book.setBookAuthor("test");
		book.setBookGenre("Drama");
		book.setBookDescription("test");
		book.setImageId("test.jpg");
		book.setText("test.pdf");

		MockMultipartFile files = new MockMultipartFile("files", "file.pdf", "text/plain", "file.pdf".getBytes());
		MockMultipartFile file = new MockMultipartFile("file", "file.pdf", "text/plain", "file.pdf".getBytes());

		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.fileUpload("/uploadBook").file("filename.pdf", "some.pdf".getBytes())
						.file(file).file(files).param("releaseDate", "02/01/2005").flashAttr("Book", book))
				.andDo(print()).andExpect(view().name("redirect:/book_catalogue")).andReturn();

		Assert.assertEquals("test", mvcResult.getModelAndView().getModel().get("newBook"));
	}

}
