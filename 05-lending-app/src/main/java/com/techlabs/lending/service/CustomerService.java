package com.techlabs.lending.service;

import java.util.List;

import com.techlabs.lending.entity.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();
	void addCustomer(Customer customer);
	void updateCustomer( Customer customer);
	void deleteCustomer( Customer customer);
	
	Customer getCustomerById(int id);
}
