package com.statthem.BuyBook.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.statthem.BuyBook.model.User;

public class PasswordMatchesValidator 
implements ConstraintValidator<PasswordMatches, Object> { 
   
  @Override
  public void initialize(PasswordMatches constraintAnnotation) {       
  }
  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context){   
      User user = (User) obj;
      return user.getUserPassword().equals(user.getMatchingPassword());    
  }     
}