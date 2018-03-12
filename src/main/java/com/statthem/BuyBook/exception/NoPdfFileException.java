package com.statthem.BuyBook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "file not found on server")
public class NoPdfFileException extends RuntimeException {
	
	String msg;

	public NoPdfFileException() {
		
	}

	public NoPdfFileException (String msg) {
    	 this.msg = msg;
	}

	public String toString() {
		return "NoPdfFileException  " + msg;
	}

}
