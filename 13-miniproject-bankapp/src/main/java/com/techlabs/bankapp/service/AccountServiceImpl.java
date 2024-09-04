package com.techlabs.bankapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.bankapp.dto.AccountDto;
import com.techlabs.bankapp.dto.PageResponse;
import com.techlabs.bankapp.entity.Account;
import com.techlabs.bankapp.entity.Customer;
import com.techlabs.bankapp.repository.AccountRepository;
import com.techlabs.bankapp.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	private Account toAccountMapper(AccountDto accountDto) {
		Account account = new Account();
		account.setBalance(accountDto.getBalance());
		
		return account;
	}
	
	private AccountDto toAccountDtoMapper(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setBalance(account.getBalance());
		
		return accountDto;
	}
	
	@Override
	public PageResponse<AccountDto> viewAllAccounts(int pageNoValue, int pageSizeValue) {
		Pageable pageable = PageRequest.of(pageNoValue, pageSizeValue);
		Page<Account> accountPage = accountRepo.findAll(pageable);
		PageResponse<AccountDto> response = new PageResponse<AccountDto>();
		
		response.setTotalPages(accountPage.getTotalPages());
		response.setTotalElements(accountPage.getTotalElements());
		response.setSize(accountPage.getSize());
		response.setLastPage(accountPage.isLast());
		
		List<AccountDto> accountDtoList = new ArrayList<>();
		
		accountPage.getContent().forEach((user)->{
			AccountDto userdto = toAccountDtoMapper(user);
			
			accountDtoList.add(userdto);
		});
		
		response.setContent(accountDtoList);
		return response;
	}

	@Override
	public AccountDto addAccount(AccountDto accountDto,int customerID) {
		Account account = toAccountMapper(accountDto);
		
		Random random = new Random();
        // Generate a number between 1000000000 (inclusive) and 10000000000 (exclusive)
        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        
        if(accountRepo.existsByAccountNumber(number)) {
        	logger.info("account number already exists");        	
        	return null;
        }
        
        account.setAccountNumber(number);
		allocateCustomerToAccount(customerID,account);
		return toAccountDtoMapper(account);
	}

	private void allocateCustomerToAccount(int customerID, Account account) {
		Customer customer = customerRepo.findById(customerID)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		account.setCustomer(customer);

		accountRepo.save(account);
		logger.info("adding account with allocation to customer:"+customerID);
	}

	@Override
	public List<Account> viewAccountDetails(int customerID) {
		Customer customer = customerRepo.findById(customerID)
				.orElseThrow(()->new NullPointerException("no customer found"));
		
		
		List<Account> accountDtoSet = customer.getAccounts();
		
		return accountDtoSet;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Account updateAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccount(long accountID) {
		accountRepo.deleteById(accountID);
		
	}



}
