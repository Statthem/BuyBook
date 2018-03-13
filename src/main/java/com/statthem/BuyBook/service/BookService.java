package com.statthem.BuyBook.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statthem.BuyBook.DAO.BookDAOImpl;
import com.statthem.BuyBook.controller.bookcontroller.BookDownloadController;
import com.statthem.BuyBook.model.Book;
import com.statthem.BuyBook.path.FolderPaths;

@Service(value = "BookService")
public class BookService implements IBookService {

	private static final Logger logger = Logger.getLogger(BookService.class);

	@Autowired
	private BookDAOImpl bookDAOImpl;

	public static final List<String> GENRES = new ArrayList<String>() {
		{
			add("Drama");
			add("Comedy");
			add("Adventure");
			add("Horror fiction");
			add("Literary realism");
			add("Romance");
			add("Satire");
			add("Tragedy");
			add("Tragicomedy");
			add("Fantasy");
		}
	};

	@Transactional
	public void addBook(Book b) {
		if (Objects.isNull(b))
			;
		this.bookDAOImpl.addBook(b);
	}

	@Transactional
	public void deleteBook(Book b) {
		this.bookDAOImpl.deleteBook(b);
	}

	@Transactional
	public Set<Book> getAllBooks() {
		if (bookDAOImpl == null)
			;
		return this.bookDAOImpl.getAllBooks();
	}

	@Transactional
	public Book getBook(long id) {
		return this.bookDAOImpl.getBookById(id);
	}

	@Transactional
	public Book getBookbyName(String bookName) {
		return this.bookDAOImpl.getBookByName(bookName);
	}

	public FileSystemResource downloadBook(String bookName) {
		// get TomCat root folder
		String rootPath = System.getProperty("catalina.home");

		File bookDir = new File(FolderPaths.BOOK_FOLDER.getPath());
		if (!bookName.contains("pdf"))
			bookName = bookName + ".pdf";

		File book = new File(bookDir + File.separator + bookName);
		if (book.exists()) {
			FileSystemResource fileSystemResource = new FileSystemResource(book);
			logger.info("book received successfull");
			return fileSystemResource;
		} else
			return null;
	}

	@Transactional
	public void modifyBook(Book b) {
		this.bookDAOImpl.ModifyBook(b);
	}

}