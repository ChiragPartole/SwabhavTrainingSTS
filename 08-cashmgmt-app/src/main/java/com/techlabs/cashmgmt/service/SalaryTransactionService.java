package com.techlabs.cashmgmt.service;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.dto.SalaryTransactionDto;
import com.techlabs.cashmgmt.entity.SalaryTransaction;

public interface SalaryTransactionService {

	PageResponse<SalaryTransaction> getAllSalaryTransactions(int pageNo,int pageSize);
	SalaryTransaction addSalaryTransaction(SalaryTransactionDto salaryTransactiondto);
	SalaryTransaction allocateSalaryToSalaryTransaction(int transactionID, int salaryID);
}
