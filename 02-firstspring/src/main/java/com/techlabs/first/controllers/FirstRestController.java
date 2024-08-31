package com.techlabs.first.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {
	
	@GetMapping("/hello")
	public String sayHello() {
		return "hello, welcome to spring";
	}
	
	@GetMapping("/bye")
	public String sayBye() {
		return "goodbye";
	}
}
