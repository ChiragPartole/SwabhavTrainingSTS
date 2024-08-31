package com.techlabs.lending.exceptions;

public class CustomerNotFoundException extends RuntimeException{

	public String getMessage() {
		return "The customer you are looking for does not exist";
	}
}
