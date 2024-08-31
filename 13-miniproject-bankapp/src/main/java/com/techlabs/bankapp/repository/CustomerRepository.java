package com.techlabs.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bankapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
