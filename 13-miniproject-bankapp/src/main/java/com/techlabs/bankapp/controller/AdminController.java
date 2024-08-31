package com.techlabs.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bankapp.dto.AccountDto;
import com.techlabs.bankapp.dto.CustomerDto;
import com.techlabs.bankapp.dto.PageResponse;
import com.techlabs.bankapp.dto.TransactionDto;
import com.techlabs.bankapp.dto.UserDto;
import com.techlabs.bankapp.entity.User;
import com.techlabs.bankapp.service.AccountService;
import com.techlabs.bankapp.service.CustomerService;
import com.techlabs.bankapp.service.TransactionService;
import com.techlabs.bankapp.service.UserService;


@RestController
@RequestMapping("/bankingapp")
public class AdminController {

	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/users")
	public ResponseEntity<User> adduser(@RequestBody UserDto userdto){
		return ResponseEntity.ok(userService.addUser(userdto));
	}
	
	@GetMapping("/customers")
	public ResponseEntity<PageResponse<CustomerDto>> viewAllCustomers
	(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize)
	{
		int pageNoValue = (pageNo != null) ? pageNo : 0; 
		int pageSizeValue = (pageSize != null) ? pageSize : Integer.MAX_VALUE;
		
		return ResponseEntity.ok(customerService.viewAllCustomers(pageNoValue, pageSizeValue));
	}
	
	@PostMapping("/customers")
	public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto){
		
		return ResponseEntity.ok(customerService.addCustomer(customerDto));
	}
	
	@GetMapping("/accounts")
	public ResponseEntity<PageResponse<AccountDto>> viewAllAccounts
	(@RequestParam(required = false) Integer pageNo
			,@RequestParam(required = false) Integer pageSize)
	{
		int pageNoValue = (pageNo != null) ? pageNo : 0; 
		int pageSizeValue = (pageSize != null) ? pageSize : Integer.MAX_VALUE;
		
		return ResponseEntity.ok(accountService.viewAllAccounts(pageNoValue, pageSizeValue));
	}
	
	@PostMapping("/accounts")
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto,
			@RequestParam int customerID){ 
		return ResponseEntity.ok(accountService.addAccount(accountDto,customerID));
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<PageResponse<TransactionDto>> viewAllTransaction(int pageNo,int pageSize){
		return ResponseEntity.ok(transactionService.viewAllTransactions(pageNo,pageSize));
	}
}
