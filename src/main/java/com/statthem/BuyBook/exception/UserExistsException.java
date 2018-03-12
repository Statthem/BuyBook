package com.statthem.BuyBook.exception;

public class UserExistsException extends Exception{
	
	String msg;
	
	public UserExistsException() {
		
	}
	
     public UserExistsException(String msg) {
    	 this.msg = msg;
	}
     
     public String toString() {
    	 return "UserExistsException " + msg;
     }
}
