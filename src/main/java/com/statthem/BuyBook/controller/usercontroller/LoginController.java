package com.statthem.BuyBook.controller.usercontroller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.statthem.BuyBook.model.User;
import com.statthem.BuyBook.service.UserService;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(ModelAndView model) {

		

		model.setViewName("LoginPage");
		return model;
	}
	
	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public RedirectView logout(RedirectAttributes attributes) {
		 attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
	        
	        return new RedirectView("/BuyBook/book_catalogue");
	}
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public ModelAndView UserInfo(ModelAndView model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		User currentUser =  userService.getUserByEmail(currentPrincipalName);

		model.addObject("currentUser", currentUser);
		model.setViewName("UserInfo");
		return model;
	}

}
