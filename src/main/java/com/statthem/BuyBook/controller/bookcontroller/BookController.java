package com.statthem.BuyBook.controller.bookcontroller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.statthem.BuyBook.controller.MainController;
import com.statthem.BuyBook.exception.NoAuthentificatedUserException;
import com.statthem.BuyBook.model.Book;
import com.statthem.BuyBook.model.User;
import com.statthem.BuyBook.service.BookService;
import com.statthem.BuyBook.service.UserService;

@Controller(value="BookController")
public class BookController {
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	private static final Logger logger = Logger.getLogger(BookController.class);
	
	
	@RequestMapping(value = "/book_catalogue/Book/{bookName}", method = RequestMethod.GET)
	public ModelAndView showBookInfo(@PathVariable("bookName") String bookName, ModelAndView modelAndview) {
		
		return new ModelAndView("forward:/resources/html/bookInfo.html");
	}
	
	
	@RequestMapping(value = "/addToFavourite/{BookId}", method = RequestMethod.GET)
	public ModelAndView addBookToFavourite(@PathVariable("BookId") String BookId, ModelAndView modelAndview){
		
		long book_id = Long.valueOf(BookId);
		Book book = bookService.getBook(book_id);
		
		//get authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null) {
			throw new NoAuthentificatedUserException("no logged in user found");
			}
		String currentPrincipalName = authentication.getName();
		
		User currentUser =  userService.getUserByEmail(currentPrincipalName);
        currentUser.setMatchingPassword(currentUser.getUserPassword());
        //
        
		userService.addBookToFavourite(currentUser, book);

		modelAndview.addObject("currentUser", currentUser);
		modelAndview.setViewName("redirect:/book_catalogue/Book/" + book.getBookName());
		
		return modelAndview;
	}
	


}
