package com.statthem.BuyBook.controller.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.statthem.BuyBook.exception.NoAuthentificatedUserException;
import com.statthem.BuyBook.model.User;
import com.statthem.BuyBook.service.UserService;

@Controller(value="UserController")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public ModelAndView getUserInfo(ModelAndView modelAndView) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null) {
			throw new NoAuthentificatedUserException("no logged in user found");
			}
		String currentPrincipalName = authentication.getName();
		
		User currentUser =  userService.getUserByEmail(currentPrincipalName);

		modelAndView.addObject("currentUser", currentUser);
		modelAndView.setViewName("UserInfo");
		return modelAndView;
	}


}
