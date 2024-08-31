package com.techlabs.jwtsecurity.service;

import com.techlabs.jwtsecurity.dto.LoginDto;
import com.techlabs.jwtsecurity.dto.RegistrationDto;
import com.techlabs.jwtsecurity.entity.User;

public interface AuthService {

	User register(RegistrationDto registrationDto);
	String login(LoginDto loginDto);
	
}
