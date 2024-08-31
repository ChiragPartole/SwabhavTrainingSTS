package com.techlabs.lending.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlabs.lending.entity.Customer;
import com.techlabs.lending.exceptions.CustomerNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
	
	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Customer> getAllCustomers() {
		TypedQuery<Customer> query = manager.createQuery("SELECT c from Customer c",Customer.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void addCustomer(Customer customer) {

		manager.persist(customer);
		
	}

	@Override
	@Transactional
	public void updateCustomer( Customer customer) {
		 Customer existingCustomer = manager.find(Customer.class, customer.getCustomerID());
		 
		 existingCustomer.setFirstname(customer.getFirstname());
		 existingCustomer.setLastname(customer.getLastname());
		 existingCustomer.setDateofbirth(customer.getDateofbirth());
		 existingCustomer.setEmail(customer.getEmail());
		 existingCustomer.setMobilenumber(customer.getMobilenumber());
		 
		 manager.merge(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(Customer customer) {
		Customer existingCustomer = manager.find(Customer.class ,customer.getCustomerID() );
		manager.remove(existingCustomer);
		
	}

	@Override
	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		Customer existingCustomer = manager.find(Customer.class, id);
		if(existingCustomer ==null) {
			throw new CustomerNotFoundException();
		}
		
		return existingCustomer;
	}

}
