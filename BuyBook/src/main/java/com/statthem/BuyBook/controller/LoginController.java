package com.statthem.BuyBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(ModelAndView model) {

		

		model.setViewName("LoginPage");
		return model;
	}
	
	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public RedirectView logout(RedirectAttributes attributes) {
		 attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
	        
	        return new RedirectView("/BuyBook/MainPage");
	}
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public ModelAndView UserInfo(ModelAndView model) {

		

		model.setViewName("UserInfo");
		return model;
	}

}
