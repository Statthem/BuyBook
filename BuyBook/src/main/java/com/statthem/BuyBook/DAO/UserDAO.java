package com.statthem.BuyBook.DAO;

import com.statthem.BuyBook.model.User;

public interface UserDAO {

	public User addUser(User user);
	
	public void deleteUser(User user);
	
	public void modifyUser(User user);

	public User getUserById(long id);
	
	public User findByEmail(String email);
}
