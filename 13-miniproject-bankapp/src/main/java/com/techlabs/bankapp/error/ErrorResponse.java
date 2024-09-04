package com.techlabs.bankapp.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {

	private int statusCode;
	private String errorMessage;
	private long timeStamp;
}
