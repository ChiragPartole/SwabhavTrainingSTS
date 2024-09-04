package com.techlabs.jwtsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.jwtsecurity.entity.Customer;
import com.techlabs.jwtsecurity.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public List<Customer> getAllCustomers() {
		
		return customerRepo.findAll();
	}

	@Override
	public Customer getCustomerById(int customerID) {
		// TODO Auto-generated method stub
		return customerRepo.findById(customerID)
				.orElseThrow(()->new NullPointerException("no customer found"));
	}

}
