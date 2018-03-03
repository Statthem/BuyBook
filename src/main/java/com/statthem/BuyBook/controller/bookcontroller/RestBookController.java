package com.statthem.BuyBook.controller.bookcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.statthem.BuyBook.model.Book;
import com.statthem.BuyBook.service.BookService;

@RestController
public class RestBookController {

	@Autowired
	BookService bookService;
	
	  @RequestMapping(value = "getBook/{bookId}", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ResponseEntity<Book> getBook(@PathVariable("bookId") Long bookId) {
	        if (bookId == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Book book = bookService.getBook(bookId);
            book.setUsersInFavourite(null);
	        
	        if (book == null) 
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        

	        return new ResponseEntity<>(book, HttpStatus.OK);

	}
}
