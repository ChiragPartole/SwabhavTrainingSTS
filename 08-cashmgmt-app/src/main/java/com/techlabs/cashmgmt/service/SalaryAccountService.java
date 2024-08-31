package com.techlabs.cashmgmt.service;

import java.util.List;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.dto.SalaryAccountDto;
import com.techlabs.cashmgmt.entity.SalaryAccount;

public interface SalaryAccountService {

	PageResponse<SalaryAccount> getAllSalaryAccounts(int pageNo,int pageSize);
	SalaryAccount addSalaryAccount(SalaryAccountDto salaryAccountDto);
	
	SalaryAccount allocateTransactionToSalaryAccount(long accountNumber, List<Integer> transactionID);
	
}
