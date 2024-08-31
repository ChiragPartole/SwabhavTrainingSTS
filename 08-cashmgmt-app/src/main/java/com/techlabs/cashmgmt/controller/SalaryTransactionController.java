package com.techlabs.cashmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.dto.SalaryTransactionDto;
import com.techlabs.cashmgmt.entity.SalaryTransaction;
import com.techlabs.cashmgmt.service.SalaryTransactionService;

@RestController
@RequestMapping("/cashmanagementapp")
public class SalaryTransactionController {

	@Autowired
	private SalaryTransactionService salaryTransactionService;
	
	@GetMapping("/salaryTransactions")
	public ResponseEntity<PageResponse<SalaryTransaction>> getSalaryTransactions(
			@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize)
	{
		 int pageNoValue = (pageNo != null) ? pageNo : 0; 
		  int pageSizeValue = (pageSize != null) ? pageSize : Integer.MAX_VALUE;
		  
		  return ResponseEntity.ok(salaryTransactionService.getAllSalaryTransactions(pageNoValue, pageSizeValue));
	}
	
	@PostMapping("/salaryTransactions")
	public ResponseEntity<SalaryTransaction> addSalaryTransaction
	(@RequestBody SalaryTransactionDto salaryTransactionDto)
	{
		return ResponseEntity.ok(salaryTransactionService.addSalaryTransaction(salaryTransactionDto));
	}
	
	@PostMapping("/salaryTransactions/salary")
	public ResponseEntity<SalaryTransaction> allocateSalaryToSalaryTransaction
	(@RequestParam int transactionID, @RequestParam int salaryID)
	{
		return ResponseEntity.ok(salaryTransactionService.allocateSalaryToSalaryTransaction
				(transactionID,salaryID));
	}
	
	
}
