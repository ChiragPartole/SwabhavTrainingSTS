package com.techlabs.jwtsecurity.service;

import java.util.List;

import com.techlabs.jwtsecurity.entity.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();
	Customer getCustomerById(int customerID);
}
