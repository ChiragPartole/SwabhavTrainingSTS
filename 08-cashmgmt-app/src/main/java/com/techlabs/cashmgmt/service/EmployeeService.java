package com.techlabs.cashmgmt.service;

import com.techlabs.cashmgmt.dto.EmployeeDto;
import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Employee;
import com.techlabs.cashmgmt.entity.SalaryAccount;

public interface EmployeeService {

	PageResponse<Employee> getAllEmployees(int pageNo,int pageSize);
	void addEmployee(EmployeeDto employeeDto);
	void deleteEmployee(int employeeID);
	void updateEmployee(Employee employee);
	
	SalaryAccount getSalaryAccountByEmployeeId(int employeeId);
	Employee allocateSalaryAccountToEmployee(int employeeID, long accountNumber);
}
