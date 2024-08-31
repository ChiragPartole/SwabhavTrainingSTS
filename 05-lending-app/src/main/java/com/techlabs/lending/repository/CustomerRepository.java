package com.techlabs.lending.repository;

import java.util.List;

import com.techlabs.lending.entity.Customer;

public interface CustomerRepository {

	List<Customer> getAllCustomers();
	void addCustomer(Customer customer);
	void updateCustomer( Customer customer);
	void deleteCustomer(Customer customer);
	
	Customer getCustomerById(int id);
}
