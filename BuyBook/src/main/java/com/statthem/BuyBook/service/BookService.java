package com.statthem.BuyBook.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statthem.BuyBook.DAO.BookDAOImpl;
import com.statthem.BuyBook.model.Book;

@Service
public class BookService {
	
@Autowired	
private BookDAOImpl bookDAOImpl;

@Transactional
public void addBook(Book b) {
	if(Objects.isNull(b))System.out.println("BookService:book is null");
	this.bookDAOImpl.addBook(b);
}

@Transactional
public void deleteBook(Book b) {
	this.bookDAOImpl.deleteBook(b);
}

@Transactional
public Set<Book> getAllBooks() {
	if(bookDAOImpl == null) System.out.println("bookDAOImpl == null!!!");
	return this.bookDAOImpl.getAllBooks();
}

@Transactional
public Book getBook(long id) {
	return this.bookDAOImpl.getBookById(id);
}

@Transactional
public void modifyBook(Book b) {
	this.bookDAOImpl.ModifyBook(b);
}

}