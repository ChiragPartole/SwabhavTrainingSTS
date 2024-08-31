package com.techlabs.lending.exceptions;

public class LoanNotFoundException extends RuntimeException {

	public String getMessage() {
		return "The loan you are looking for does not exist";
	}
}
