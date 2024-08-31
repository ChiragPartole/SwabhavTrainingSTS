package com.techlabs.di.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.di.entity.Computer;
import com.techlabs.di.entity.Harddisk;

@RestController
public class ComputerController {

	
	@Autowired
	private Computer computer;
	
	@Autowired
	private Harddisk harddisk;
	
	@GetMapping("/computer")
	public Computer getComputer() {
		return computer;
	}
	
	@GetMapping("/harddisk")
	public Harddisk getHarddisk() {
		return harddisk;
	}
	
	
}
