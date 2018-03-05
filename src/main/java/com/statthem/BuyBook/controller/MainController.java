package com.statthem.BuyBook.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
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
import com.statthem.BuyBook.path.FolderPaths;
import com.statthem.BuyBook.service.BookService;
import com.statthem.BuyBook.service.UserService;

@Controller
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);

	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/book_catalogue", method = RequestMethod.GET)
	public ModelAndView  getMainPage(ModelAndView modelAndView) {

		logger.info("in getMainPage method");

		modelAndView.setViewName("MainPage");
		modelAndView.addObject("allBooks", bookService.getAllBooks());
		modelAndView.addObject("genreList",BookService.GENRES);
		return modelAndView;

	}

	@RequestMapping(value = "/book_catalogue/{OrderBy}", method = RequestMethod.GET)
	public ModelAndView OrderBy(@PathVariable("OrderBy") String OrderBy, ModelAndView modelAndView,
			HttpSession session) {
		List<Book> allBooks;
		
	if(session.getAttribute("filteredBooks") != null)
			allBooks = new ArrayList<>((Set<Book>) session.getAttribute("filteredBooks"));
		
		allBooks = new ArrayList<>(bookService.getAllBooks());
		
		
		allBooks.forEach(book -> System.out.println(book.getReleaseDate()));
        allBooks = (sort(OrderBy,allBooks));
      
		session.setAttribute("sortedBooks", allBooks);
		session.setAttribute("OrderedBy", OrderBy);
		
		modelAndView.addObject("genreList",BookService.GENRES);
		modelAndView.setViewName("redirect:/book_catalogue");

		return modelAndView;
	}
	
	@RequestMapping(value = "/book_catalogue/filter/{filterBy}", method = RequestMethod.GET)
	public ModelAndView FilterBy(@PathVariable("filterBy") String filterBy, ModelAndView modelAndView,
			HttpSession session) {
		
		 String genre = filterBy.substring(8);
		 
		List<Book> allBooks = new ArrayList<>(bookService.getAllBooks());
		if(genre.equals("None")) {
			session.removeAttribute("filteredBooks");
			session.removeAttribute("filteredBy");
			return new ModelAndView("redirect:/book_catalogue");
		}
		
		Set<Book> filteredBooks = new HashSet<Book>(filter(genre,allBooks));
		
		session.setAttribute("filteredBooks", filteredBooks);
		session.setAttribute("filteredBy", genre);

		modelAndView.addObject("genreList",BookService.GENRES);
		modelAndView.setViewName("MainPage");

		return modelAndView;
	}
	
	
	@RequestMapping(value = "/book_catalogue/search", method = RequestMethod.POST)
	
	public ModelAndView searchBook(@RequestParam(value="bookName", required=true)  String bookName,
			ModelAndView modelAndView,Model model,HttpSession session) {
		
		bookName = bookName.trim();
		Book book = bookService.getBookbyName(bookName);
				if(book==null) {
					model.addAttribute("bookNotFound",bookName);
					return new ModelAndView("MainPage");
				}
       List<Book> singleBook = new ArrayList<>();
       singleBook.add(book);
		session.setAttribute("searchedBook", singleBook);

		
		modelAndView.setViewName("MainPage");
		return modelAndView;
	}
	
	
	
   //copying files from backup folder to webapp/resources
	@PostConstruct
	private void copyFiles() {
		File imagesFrom = new File(FolderPaths.BACKUP_IMAGE_FOLDER.getPath());
		File booksFrom = new File(FolderPaths.BACKUP_BOOK_FOLDER.getPath());
		
		
		File imagesTo = new File(FolderPaths.IMAGE_FOLDER.getPath());
		File booksTo = new File(FolderPaths.BOOK_FOLDER.getPath());

		 try {
		if (booksFrom.isDirectory()) {
			FileUtils.copyDirectory(imagesFrom, imagesTo);
		}
		if (imagesFrom.isDirectory()) {
			FileUtils.copyDirectory(booksFrom, booksTo);
		}
		 } catch (IOException e) {		
				e.printStackTrace();
			}
	}
	
	private List<Book> filter(String genre,List<Book> allBooks) {
	
			List<Book> filteredBooks = new ArrayList<>();
			

			allBooks.stream().filter(book -> book.getBookGenre().equals(genre))
            .forEach(book -> filteredBooks.add(book));			
		
		return filteredBooks;
	}
	
	private List<Book> sort(String OrderBy,List<Book> allBooks) {
		
		
		if (OrderBy.equals("OrderByName"))
			Collections.sort(allBooks, byNameComparator);
		if (OrderBy.equals("OrderByRelease_date"))
			Collections.sort(allBooks, byDateComparator);
		if (OrderBy.equals("OrderByRating"))
			Collections.sort(allBooks, byRatingComparator);
		
		return allBooks;
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
