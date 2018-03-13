package com.statthem.BuyBook.DAO;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.statthem.BuyBook.model.Book;

@Repository(value="BookDAOImpl")
public class BookDAOImpl implements BookDAO {
	
	Logger logger = Logger.getLogger(BookDAOImpl.class);
	
	@Autowired
	@Qualifier(value="hibernate4AnnotatedSessionFactory")
	SessionFactory sessionFactory;
	
	private static final String DEFAULT_IMAGE_ID = "book_cover.jpg";

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public void addBook(Book book) {
		Session session = this.sessionFactory.getCurrentSession();
		if( book.getImageId() == null || book.getImageId().length() == 0) book.setImageId(DEFAULT_IMAGE_ID);
		session.persist(book);
		logger.info("new book added succesfully");
	}
	
	

	public void deleteBook(Book book) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(book);
		logger.info("book deleted succesfully");
	}
	
	@SuppressWarnings("unchecked")
	public Set<Book> getAllBooks() {
		if(sessionFactory == null)  logger.info("sessionFactory == null");
		
		
		Session session = this.sessionFactory.getCurrentSession();
		Set<Book> allBooks = null;
		
		Query hqlQuery = session.createQuery("Select b from Book b");
		allBooks = new HashSet<Book>(hqlQuery.list());
		if(allBooks.isEmpty()) {
			logger.info("book Set is emply");
		}else {
			
		}
		return allBooks;
	}
	
	
	public Book getBookById(long book_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Book book = null;
		
		Query hqlQuery = session.createQuery("Select b from Book b where b.id = :id");
		hqlQuery.setParameter("id", book_id);
		book = (Book)  hqlQuery.uniqueResult();
		if(Objects.isNull(book)) {
			logger.info("book is null");
		}else {
		}
		return book;
	}
	
	public Book getBookByName(String bookName) {
		Session session = this.sessionFactory.getCurrentSession();
		Book book = null;
		
		Query hqlQuery = session.createQuery("Select b from Book b where b.bookName = :bookName");
		hqlQuery.setParameter("bookName", bookName);
		book = (Book)  hqlQuery.uniqueResult();
		if(Objects.isNull(book)) {
			logger.info("book is null");
			return null;
		}else {
		}
		return book;
	}
	
	
	
	public void ModifyBook(Book book) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(book);
		logger.info("book modifyed succesfully");
	}
	

}
