package com.statthem.BuyBook.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statthem.BuyBook.DAO.UserDAOImpl;
import com.statthem.BuyBook.exception.EmailExistsExeption;
import com.statthem.BuyBook.model.User;

@Service
public class UserService {
	
@Autowired
private UserDAOImpl userDAOImpl;

@Transactional
public User registerNewUser(User u) throws EmailExistsExeption {
        
        if (emailExist(u.getUserEmail())) {  
            throw new EmailExistsExeption(
              "There is an account with that email adress: "
              +  u.getUserEmail());
        }
	
        User user = new User();    
        user.setUserName(u.getUserName());
        user.setUserPassword(u.getUserPassword());
        user.setUserEmail(u.getUserEmail());
        
	
	return this.userDAOImpl.addUser(u);
}

private boolean emailExist(String email) {
	User user = userDAOImpl.findByEmail(email);
    if (user != null) {
        return true;
    }
    return false;
}


@Transactional
public void deleteUser(User u) {
	this.userDAOImpl.deleteUser(u);
}

@Transactional
public void modifyUser1(User u) {
	this.userDAOImpl.modifyUser(u);
}

@Transactional
public User getUser(long id) {
	return this.userDAOImpl.getUserById(id);
}


}