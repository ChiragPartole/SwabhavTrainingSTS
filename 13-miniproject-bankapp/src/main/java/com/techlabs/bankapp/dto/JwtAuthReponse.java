package com.techlabs.bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class JwtAuthReponse {

	private String accessToken;
	private String tokenType= "Bearer";
}
