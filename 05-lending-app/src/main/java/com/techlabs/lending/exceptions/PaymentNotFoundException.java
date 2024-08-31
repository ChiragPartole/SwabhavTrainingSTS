package com.techlabs.lending.exceptions;

public class PaymentNotFoundException extends RuntimeException{
	public String getMessage() {
		return "The Payment you are looking for does not exist";
	}
}
