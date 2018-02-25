package com.statthem.BuyBook.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.statthem.BuyBook.DAO.BookDAOImpl;
import com.statthem.BuyBook.model.Book;
import com.statthem.BuyBook.model.User;
import com.statthem.BuyBook.service.BookService;

@Controller
public class MainController {

	
	Logger logger = Logger.getLogger(MainController.class);

	@Autowired
	BookService bookService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView defaultController(ModelAndView modelAndView) {
		// model.addAllAttributes(toMap((Set<Book>)bookDAOImpl.getAllBooks()));
		modelAndView.addObject("allBooks", bookService.getAllBooks());
		modelAndView.setViewName("MainPage");
		return modelAndView;
	}

	@RequestMapping(value = "/MainPage/{OrderBy}", method = RequestMethod.GET)
	public ModelAndView OrderBy(@PathVariable("OrderBy") String OrderBy, ModelAndView modelAndView,
			HttpSession session) {
		List<Book> allBooks = new ArrayList<>(bookService.getAllBooks());

		logger.info("in OrderBy method");

		if (OrderBy.equals("OrderByName"))
			Collections.sort(allBooks, byNameComparator);
		if (OrderBy.equals("OrderByRelease_date"))
			Collections.sort(allBooks, byDateComparator);
		if (OrderBy.equals("OrderByRating"))
			Collections.sort(allBooks, byRatingComparator);

		session.setAttribute("sortedBooks", allBooks);
		session.setAttribute("OrderedBy", OrderBy);

		modelAndView.setViewName("MainPage");

		return modelAndView;
	}

	@RequestMapping(value = "/MainPage", method = RequestMethod.GET)
	public String getMainPage(Model model) {

		logger.info("in getMainPage method");

		// model.addAllAttributes(toMap((Set<Book>)bookDAOImpl.getAllBooks()));
		model.addAttribute("allBooks", bookService.getAllBooks());
		return "MainPage";

	}

	@RequestMapping(value = "/RegistrationPage", method = RequestMethod.GET)
	public String getRegistrationPage(Model model) {

		return "RegistrationPage";
	}

	@RequestMapping(value = "/MainPage/Book", method = RequestMethod.POST)
	public ModelAndView getBookInfo(@RequestParam("id") String id, ModelAndView modelAndview) {

		long book_id = Long.valueOf(id);

		Book book = bookService.getBook(book_id);

		modelAndview.setViewName("BookInfo");
		modelAndview.addObject("CurrentBook", book);

		return modelAndview;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getRegistration(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		
	//	return "forward:resources/html/registration.html";
		return "RegistrationPage";
	}
	
	
	


	private Map<String, Book> toMap(Set<Book> set) {
		Map<String, Book> map = new HashMap<>();

		set.forEach((t) -> map.put(((com.statthem.BuyBook.model.Book) t).getBookName(), t));
		return map;
	}

	private Comparator<Book> byNameComparator = new Comparator<Book>() {
		@Override
		public int compare(Book book1, Book book2) {

			return book1.getBookName().compareToIgnoreCase(book2.getBookName());
		}
	};

	private Comparator<Book> byRatingComparator = new Comparator<Book>() {
		@Override
		public int compare(Book book1, Book book2) {

			return book1.getBookRating() - book2.getBookRating();
		}
	};

	private Comparator<Book> byDateComparator = new Comparator<Book>() {
		@Override
		public int compare(Book book1, Book book2) {

			return book1.getReleaseDate().compareTo(book2.getReleaseDate());
		}
	};

}
