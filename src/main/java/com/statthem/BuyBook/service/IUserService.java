package com.statthem.BuyBook.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.statthem.BuyBook.exception.EmailExistsExeption;
import com.statthem.BuyBook.model.Book;
import com.statthem.BuyBook.model.User;

public interface IUserService {
	
	
	public User registerNewUser(User u) throws EmailExistsExeption ;

	public User getUserByEmail(String email); 
		
	public void deleteUser(User u);
	
	public void modifyUser(User u);

	public User getUser(long id);
	
	public void addBookToFavourite(User user,Book book);


}
