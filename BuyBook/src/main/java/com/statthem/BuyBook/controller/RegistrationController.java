package com.statthem.BuyBook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.statthem.BuyBook.model.User;
import com.statthem.BuyBook.service.UserService;
import com.statthem.BuyBook.exception.*;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/registration",method = RequestMethod.POST)
	public ModelAndView  registerUserAccount(@ModelAttribute("user") @Valid User user,Model model,BindingResult result, WebRequest request, Errors errors) {
		
		System.out.println(errors.getErrorCount());
		System.out.println(errors.toString());
		User registered = new User();
	    if (!result.hasErrors()) {
	        registered = createUserAccount(user, result);
	    }
	    if (registered == null) {
	        result.rejectValue("email", "message.regError");
	    }
	    if (result.hasErrors()) {
	        return new ModelAndView("registration", "user", user);
	    } 
	    else {
	        return new ModelAndView("successRegister", "user", user);
	    }
	}
	
	private User createUserAccount(User user, BindingResult result) {
	    User registered = null;
	    try {
	        registered = userService.registerNewUser(user);
	    } catch (EmailExistsExeption e) {
	        return null;
	    }    
	    return registered;
	}
		

}
