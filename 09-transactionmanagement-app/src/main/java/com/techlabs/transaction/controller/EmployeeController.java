package com.techlabs.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.transaction.entity.Employee;
import com.techlabs.transaction.service.EmployeeService;

@RestController
@RequestMapping("/transactionmanagement")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws Exception{
		Employee employeeSavedToDB = this.employeeService.addEmployee(employee);
		return new ResponseEntity<Employee>(employeeSavedToDB, HttpStatus.CREATED);
	}
}

