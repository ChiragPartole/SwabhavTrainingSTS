package com.techlabs.jpacrud.exceptions;

public class StudentNotFoundException extends RuntimeException{
	
	public String getMessage() {
		return "The user you are searching for is not present";
	}

}
