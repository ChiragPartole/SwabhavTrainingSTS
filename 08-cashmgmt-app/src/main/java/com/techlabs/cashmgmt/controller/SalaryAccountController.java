package com.techlabs.cashmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.dto.SalaryAccountDto;
import com.techlabs.cashmgmt.entity.SalaryAccount;
import com.techlabs.cashmgmt.service.SalaryAccountService;

@RestController
@RequestMapping("/cashmanagementapp")
public class SalaryAccountController {

	@Autowired
	private SalaryAccountService salaryAccountService;
	
	@GetMapping("/salaryAccounts")
	public ResponseEntity<PageResponse<SalaryAccount>> getAllSalaryAccounts(
			@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize)
	{
		  int pageNoValue = (pageNo != null) ? pageNo : 0; 
		  int pageSizeValue = (pageSize != null) ? pageSize : Integer.MAX_VALUE;
		  
		  return ResponseEntity.ok(salaryAccountService.getAllSalaryAccounts(pageNoValue, pageSizeValue));
		  
	}
	
	
	@PostMapping("/salaryAccounts")
	public ResponseEntity<SalaryAccount> addSalaryAccount(@RequestBody SalaryAccountDto salaryAccountDto)
	{
		return ResponseEntity.ok(salaryAccountService.addSalaryAccount(salaryAccountDto));
	}
	
	@PostMapping("/salaryAccounts/transactions")
	public ResponseEntity<SalaryAccount> allocateTransactionToSalaryAccount(@RequestParam long accountNumber,@RequestParam List<Integer> transactionID)
	{
		return ResponseEntity.ok(salaryAccountService.allocateTransactionToSalaryAccount(accountNumber,transactionID));
	}
	
	
	
	
	
	
	
	
	
	
}
