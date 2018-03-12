package com.statthem.BuyBook.exception;

public class BookExistsException extends Exception {

	String msg;

	public BookExistsException() {
	}

	public BookExistsException(String msg) {
		this.msg = msg;
	}
	
	 public String toString() {
    	 return "BookExistsException  " + msg;
     }

}
