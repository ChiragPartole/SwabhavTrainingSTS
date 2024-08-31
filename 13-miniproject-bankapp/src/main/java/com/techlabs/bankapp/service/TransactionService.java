package com.techlabs.bankapp.service;

import java.util.List;

import com.techlabs.bankapp.dto.PageResponse;
import com.techlabs.bankapp.dto.TransactionDto;
import com.techlabs.bankapp.entity.Transaction;

import jakarta.servlet.http.HttpSession;

public interface TransactionService {
	
	PageResponse<TransactionDto> viewAllTransactions(int pageNo,int pageSize);
	
	TransactionDto addTransaction(TransactionDto transactionDto);

	List<TransactionDto> viewPassbook(long accountNumber);
	
	void deleteTransaction(int transactionID);

	
	
	
}
