package com.techlabs.empDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.empDB.entity.Employee;
import com.techlabs.empDB.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepo;
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepo.getAllEmployees();
	}
	
	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		 employeeRepo.addEmployee(employee);
	}

}
