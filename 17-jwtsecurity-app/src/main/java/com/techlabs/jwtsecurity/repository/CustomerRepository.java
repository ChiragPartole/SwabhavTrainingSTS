package com.techlabs.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.jwtsecurity.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
