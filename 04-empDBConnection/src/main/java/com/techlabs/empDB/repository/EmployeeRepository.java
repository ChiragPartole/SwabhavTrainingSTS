package com.techlabs.empDB.repository;

import java.util.List;

import com.techlabs.empDB.entity.Employee;

public interface EmployeeRepository {

	List<Employee> getAllEmployees();
	void addEmployee(Employee employee);
	
}
