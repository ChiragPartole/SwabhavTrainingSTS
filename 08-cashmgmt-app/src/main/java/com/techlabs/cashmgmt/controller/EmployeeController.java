package com.techlabs.cashmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.cashmgmt.dto.EmployeeDto;
import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Employee;
import com.techlabs.cashmgmt.entity.SalaryAccount;
import com.techlabs.cashmgmt.service.EmployeeService;

@RestController
@RequestMapping("/cashmanagementapp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("employees")
	public ResponseEntity<PageResponse<Employee>> getAllEmployees(
			@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize)
	{
		  int pageNoValue = (pageNo != null) ? pageNo : 0; 
		  int pageSizeValue = (pageSize != null) ? pageSize : Integer.MAX_VALUE;
		  
		  return ResponseEntity.ok(employeeService.getAllEmployees(pageNoValue, pageSizeValue));
		  
	}
	
	@PostMapping("/employees")
	public String addEmployee(@RequestBody EmployeeDto employeeDto) {
		
		employeeService.addEmployee(employeeDto);
		return "added successfully";
		
		
	}
	
	@PutMapping("/employees")
	public String updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
		return "updated successfully";
	}
	
	@GetMapping("/employees/salaryaccount")
	public ResponseEntity<SalaryAccount> getSalaryAccountByEmployeeId(@RequestParam int employeeId)
	{
		return ResponseEntity.ok(employeeService.getSalaryAccountByEmployeeId(employeeId));
	}
	
	@DeleteMapping("/employees")
	public String deleteEmployee(@RequestParam int employeeID) {
		employeeService.deleteEmployee(employeeID);
		return "deleted successfully";
	}
	
	@PostMapping("/employees/salaryaccount")
	public ResponseEntity<Employee> allocateSalaryAccountToEmployee(@RequestParam int employeeID,@RequestParam long accountNumber)
	{
		return ResponseEntity.ok(employeeService.allocateSalaryAccountToEmployee(employeeID,accountNumber));
	}
	
	
	
}
