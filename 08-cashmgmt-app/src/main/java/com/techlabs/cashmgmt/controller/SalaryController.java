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

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Salary;
import com.techlabs.cashmgmt.service.SalaryService;

@RestController
@RequestMapping("/cashmanagementapp")
public class SalaryController {

	@Autowired
	private SalaryService salaryService;
	
	@GetMapping("/salary")
	public ResponseEntity<PageResponse<Salary>> getAllSalaryDetails(
			@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize)
	{
		 int pageNoValue = (pageNo != null) ? pageNo : 0; 
		  int pageSizeValue = (pageSize != null) ? pageSize : Integer.MAX_VALUE;
		  
		  return ResponseEntity.ok(salaryService.getSalaryDetails(pageNoValue, pageSizeValue));
	}
	
	@PostMapping("/salary")
	public String addSalaryDetails(@RequestBody Salary salary) {
		salaryService.addSalaryDetails(salary);
		return "added successfully";
	}
	
	@PutMapping("/salary")
	public String updateSalaryDetails(@RequestBody Salary salary) {
		salaryService.updateSalaryDetails(salary);
		return "updated successfully";
	}
	
	@DeleteMapping("/salary")
	public String deleteSalaryDetails(@RequestParam int salaryID) {
		salaryService.deleteSalaryDetails(salaryID);
		return "deleted successfully";
	}
	
//	@GetMapping("/salary/salarytransaction")
//	public ResponseEntity<SalaryTransaction> getTransactionByID(@RequestParam int transactionID){
//		return ResponseEntity.ok(salaryService.getSalaryTransactionByID(transactionID));
//	}
}
