package com.techlabs.bankapp.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bankapp.dto.AccountDto;
import com.techlabs.bankapp.dto.CustomerDto;
import com.techlabs.bankapp.dto.TransactionDto;
import com.techlabs.bankapp.service.AccountService;
import com.techlabs.bankapp.service.CustomerService;
import com.techlabs.bankapp.service.TransactionService;


@RestController
@RequestMapping("/bankingapp")
public class UserController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/users")
	public ResponseEntity<Set<AccountDto>> viewAccountDetails(int customerID){
		return ResponseEntity.ok(accountService.viewAccountDetails(customerID));
	}
	
	@PostMapping("/transactions")
	public ResponseEntity<TransactionDto> addNewTransaction(@RequestBody TransactionDto transactionDto){
		return ResponseEntity.ok(transactionService.addTransaction(transactionDto));
	}
	
	@GetMapping("/transactions/passbook")
	public ResponseEntity<List<TransactionDto>> viewPassbook(long accountNumber){
		return ResponseEntity.ok(transactionService.viewPassbook(accountNumber));
	}
	
	@PutMapping("/users")
	public ResponseEntity<CustomerDto> updateUser(@RequestBody CustomerDto customerDto,
			@RequestParam int customerID){
		return ResponseEntity.ok(customerService.updateCustomer(customerDto,customerID));
	}
	
	
	
}
