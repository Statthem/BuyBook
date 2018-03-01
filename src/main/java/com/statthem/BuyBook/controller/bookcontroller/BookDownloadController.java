package com.statthem.BuyBook.controller.bookcontroller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.statthem.BuyBook.service.BookService;

@Controller
public class BookDownloadController {
	
	@Autowired
	BookService bookService;
	
	private static final Logger logger = Logger.getLogger(BookDownloadController.class);
	
	@RequestMapping(value = "/downloadBook/{bookName}", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource getBook(@PathVariable("bookName") String bookName) {
		logger.info("in downloadbook");
		FileSystemResource bookAsResource = bookService.downloadBook(bookName,logger);
		return bookAsResource;
	}

}
