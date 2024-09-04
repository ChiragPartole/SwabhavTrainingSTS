package com.techlabs.bankapp.exception;

public class SameAccountInTransferException extends RuntimeException{


	public String getMessage() {
		return "Cannot have same sender and receiver account number in TRANSFER ";
	}
}
