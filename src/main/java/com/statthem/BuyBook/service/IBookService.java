package com.statthem.BuyBook.service;

import java.util.Set;

import com.statthem.BuyBook.model.Book;

public interface IBookService {
	
	public void addBook(Book b);
	
	public void deleteBook(Book b);
	
	public Book getBook(long id) ;
	
	public Set<Book> getAllBooks();
	
	public void modifyBook(Book b);

}
