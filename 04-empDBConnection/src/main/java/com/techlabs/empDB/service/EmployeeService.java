package com.techlabs.empDB.service;

import java.util.List;

import com.techlabs.empDB.entity.Employee;


public interface EmployeeService {

	public List<Employee> getAllEmployees();
	public void addEmployee(Employee employee);
}
