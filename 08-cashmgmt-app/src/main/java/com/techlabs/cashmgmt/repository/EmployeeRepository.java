package com.techlabs.cashmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.cashmgmt.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	
}
