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

import com.techlabs.cashmgmt.dto.BankDto;
import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Bank;
import com.techlabs.cashmgmt.service.BankService;

@RestController
@RequestMapping("/cashmanagementapp")
public class BankController {

	@Autowired
	private BankService bankService;
	
	@GetMapping("/banks")
	public ResponseEntity<PageResponse<Bank>> getAllBanks(
			@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize)
	{
		 int pageNoValue = (pageNo != null) ? pageNo : 0; 
		  int pageSizeValue = (pageSize != null) ? pageSize : Integer.MAX_VALUE;
		  
		  return ResponseEntity.ok(bankService.getAllBanks(pageNoValue, pageSizeValue));
	}
	
	@PostMapping("/banks")
	public ResponseEntity<Bank> addBank(@RequestBody BankDto bankdto){
		return ResponseEntity.ok(bankService.addBank(bankdto));
	}
	
	@PostMapping("/banks/salaryaccounts")
	public ResponseEntity<Bank> allocateSalaryAccountToBank
	(@RequestParam int bankID, @RequestParam List<Long> salaryAccountNumbers){
		return ResponseEntity.ok(bankService.allocateSalaryAccountToBank(bankID,salaryAccountNumbers));
	}
	
}
