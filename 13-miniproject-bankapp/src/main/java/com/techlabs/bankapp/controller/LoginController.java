package com.techlabs.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bankapp.dto.UserDto;
import com.techlabs.bankapp.service.UserService;

@RestController
@RequestMapping("/bankingapp")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto userDto){
		if(!userService.login(userDto))
			return ResponseEntity.ok("Invalid credentials");

		return ResponseEntity.ok("login success, logged in as "+userDto.getRole());
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(){
		
		
		return ResponseEntity.ok("logging out");
	}
}
