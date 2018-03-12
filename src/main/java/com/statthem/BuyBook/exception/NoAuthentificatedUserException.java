package com.statthem.BuyBook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.EXPECTATION_FAILED, reason="no authentificated user found")
public class NoAuthentificatedUserException extends RuntimeException{
	
		
	String msg;

	public NoAuthentificatedUserException() {

	}

	public NoAuthentificatedUserException(String msg) {
		this.msg = msg;
	}

	public String toString() {
		return "NoAuthentificatedUserException  " + msg;
	}

}
