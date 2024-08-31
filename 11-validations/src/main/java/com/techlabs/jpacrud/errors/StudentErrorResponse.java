package com.techlabs.jpacrud.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentErrorResponse {

	private int statusCode;
	private String errorMessage;
	private long timeStamp;
}
