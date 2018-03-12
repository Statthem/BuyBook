package com.statthem.BuyBook.exception;

public class EmailExistsExeption extends Exception {

	String msg;

	public EmailExistsExeption() {

	}

	public EmailExistsExeption(String msg) {
        this.msg = msg;
	}
	
	public String toString() {
		return "EmailExistsExeption " + msg;
	}

}
