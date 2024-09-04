package com.techlabs.bankapp.exception;

public class InsufficientBalanceException extends RuntimeException{

	public String getMessage() {
		return "Insufficient Balance ";
	}
}
