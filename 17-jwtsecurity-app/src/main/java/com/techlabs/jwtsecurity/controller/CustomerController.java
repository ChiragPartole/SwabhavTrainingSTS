package com.techlabs.jwtsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.jwtsecurity.entity.Customer;
import com.techlabs.jwtsecurity.service.CustomerService;

@RestController
@RequestMapping("/app")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	@GetMapping("/customers/{customerID}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerID){
		return ResponseEntity.ok(customerService.getCustomerById(customerID));
	}
}
