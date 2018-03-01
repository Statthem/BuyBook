package com.statthem.BuyBook.controller.usercontroller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
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

	private static final Logger logger = Logger.getLogger(RegistrationController.class);
	
	@Autowired
	private UserService userService;


	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getRegistration(Model model) {

		User user = new User();
		model.addAttribute("user", user);

		return "RegistrationPage";
	}
	
	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid User user, BindingResult result, Model model,
			WebRequest request, Errors errors) {

		logger.debug("error Count " + errors.getErrorCount());

		List<String> errorsList = new ArrayList<>();

		result.getAllErrors().forEach((error) -> errorsList.add((error.getDefaultMessage())));

		User registered = new User();
		if (!result.hasErrors()) {
			registered = createUserAccount(user, result);
		}
		if (registered == null) {
			result.rejectValue("email", "message.regError");
		}
		
		if (result.hasErrors()) {
			return new ModelAndView("RegistrationPage", "errorsList", errorsList);
		} else {
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
