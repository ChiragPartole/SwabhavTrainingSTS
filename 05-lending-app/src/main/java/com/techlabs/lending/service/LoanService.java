package com.techlabs.lending.service;

import java.util.List;

import com.techlabs.lending.entity.Loan;

public interface LoanService {

	List<Loan> getAllLoans();
	void addLoan(Loan loan);
	void updateLoan( Loan loan);
	void deleteLoan(int loanid);
	
	Loan getLoanById(int id);
}
