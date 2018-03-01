package com.statthem.BuyBook.service;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;

import com.statthem.BuyBook.model.Book;

public interface IBookService {
	
	public void addBook(Book b);
	
	public void deleteBook(Book b);
	
	public Book getBook(long id) ;
	
	public Set<Book> getAllBooks();
	
	public FileSystemResource downloadBook(String bookName,Logger logger);
	
	public void modifyBook(Book b);

}
