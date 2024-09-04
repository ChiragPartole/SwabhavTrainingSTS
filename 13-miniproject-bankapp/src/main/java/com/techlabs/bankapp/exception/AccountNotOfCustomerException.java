package com.techlabs.bankapp.exception;

public class AccountNotOfCustomerException extends RuntimeException{

	public String getMessage() {
		return "Account number does not belong to the customer ";
	}
}