package com.techlabs.lending.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.lending.entity.Customer;
import com.techlabs.lending.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepo.getAllCustomers();
	}

	@Override
	public void addCustomer(Customer customer) {
		customerRepo.addCustomer(customer);
		
	}

	@Override
	public void updateCustomer( Customer customer) {
		customerRepo.updateCustomer( customer);
		
	}

	@Override
	public void deleteCustomer( Customer customer) {
		customerRepo.deleteCustomer(customer);
		
	}

	@Override
	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		return customerRepo.getCustomerById(id);
	}

}
