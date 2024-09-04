package com.techlabs.bankapp.service;

import com.techlabs.bankapp.dto.LoginDto;
import com.techlabs.bankapp.dto.RegistrationDto;
import com.techlabs.bankapp.entity.User;

public interface AuthService {

	User register(RegistrationDto registrationDto);
	String login(LoginDto loginDto);
	
}
