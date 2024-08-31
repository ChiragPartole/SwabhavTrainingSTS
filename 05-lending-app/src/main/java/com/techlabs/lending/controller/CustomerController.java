package com.techlabs.lending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.lending.entity.Customer;
import com.techlabs.lending.service.CustomerService;



@RestController
@RequestMapping("/lendingapp")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllEmployees(){
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
	@GetMapping("/customers/{customerID}")
	public ResponseEntity<Customer> getAllEmployees(@PathVariable int customerID){
		return ResponseEntity.ok(customerService.getCustomerById(customerID));
	} 
	
	@PostMapping("/customers")
	public String addCustomers(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return "added successfully!!";
	}
	
	@PostMapping("/updatecustomer")
	public String updateCustomer(@RequestBody Customer customer) {
		
		customerService.updateCustomer(customer);
		return "updated successfully!!";
	}
	
	@DeleteMapping("/deletecustomer")
	public String deleteCustomer(@RequestBody Customer customer) {
		customerService.deleteCustomer(customer);
		return "deleted successfully!!";
	}
	
	
}
