package com.statthem.BuyBook.DAO;

import java.util.Set;

import com.statthem.BuyBook.model.Book;

public interface BookDAO {
	
	public Set<Book> getAllBooks();
	
	public void addBook(Book book);
	
	public void deleteBook(Book book);
	
	public void ModifyBook(Book book);
	
	public Book getBookById(long book_id);

}
