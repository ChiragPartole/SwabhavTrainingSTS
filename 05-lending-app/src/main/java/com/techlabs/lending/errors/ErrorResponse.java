package com.techlabs.lending.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorResponse {

	private int statusCode;
	private String errorMessage;
	private long timeStamp;
}
