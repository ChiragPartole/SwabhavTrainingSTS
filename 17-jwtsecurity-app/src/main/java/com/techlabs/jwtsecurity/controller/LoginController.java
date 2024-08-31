package com.techlabs.jwtsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.jwtsecurity.dto.JwtAuthReponse;
import com.techlabs.jwtsecurity.dto.LoginDto;
import com.techlabs.jwtsecurity.dto.RegistrationDto;
import com.techlabs.jwtsecurity.entity.User;
import com.techlabs.jwtsecurity.service.AuthService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegistrationDto registrationDto){
		return ResponseEntity.ok(authService.register(registrationDto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthReponse> login(@RequestBody LoginDto loginDto){
		
		String token = authService.login(loginDto);
		JwtAuthReponse jwtAuthResponse = new JwtAuthReponse();
		jwtAuthResponse.setAccessToken(token);
		return ResponseEntity.ok(jwtAuthResponse);
	}
	
}
