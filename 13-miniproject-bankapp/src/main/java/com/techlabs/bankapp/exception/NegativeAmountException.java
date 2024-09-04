package com.techlabs.bankapp.exception;

public class NegativeAmountException extends RuntimeException{

	public String getMessage() {
		return "Negative amount entered ";
	}
}