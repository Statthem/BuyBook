package com.statthem.BuyBook.service;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;

import com.statthem.BuyBook.model.Book;

public interface IBookService {
	
	public void addBook(Book b);
	
	public void deleteBook(Book b);
	
	public Book getBook(long id) ;
	
	public Book getBookbyName(String bookName) ;
	
	public Set<Book> getAllBooks();
	
	public FileSystemResource downloadBook(String bookName);
	
	public void modifyBook(Book b);

}
