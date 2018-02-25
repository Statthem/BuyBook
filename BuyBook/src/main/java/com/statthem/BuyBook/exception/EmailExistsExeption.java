package com.statthem.BuyBook.exception;

public class EmailExistsExeption extends Exception {

	String msg;

	public EmailExistsExeption() {

	}

	public EmailExistsExeption(String msg) {

	}
	
	public String toString() {
		return "EmailExistsExeption" +msg;
	}

}
