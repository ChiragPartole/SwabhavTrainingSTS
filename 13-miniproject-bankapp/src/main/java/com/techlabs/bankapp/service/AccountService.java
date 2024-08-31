package com.techlabs.bankapp.service;

import java.util.Set;

import com.techlabs.bankapp.dto.AccountDto;
import com.techlabs.bankapp.dto.PageResponse;
import com.techlabs.bankapp.entity.Account;


public interface AccountService {

	PageResponse<AccountDto> viewAllAccounts(int pageNoValue, int pageSizeValue);
	AccountDto addAccount(AccountDto accountDto,int customerID);
	Account updateAccount(AccountDto accountDto);
	
	
	
	Set<AccountDto> viewAccountDetails(int customerID);

	void deleteAccount(long accountID);
}
