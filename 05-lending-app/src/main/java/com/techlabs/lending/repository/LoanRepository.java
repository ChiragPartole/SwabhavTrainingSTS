package com.techlabs.lending.repository;

import java.util.List;

import com.techlabs.lending.entity.Loan;

public interface LoanRepository {

	List<Loan> getAllLoans();
	void addLoan(Loan loan);
	void updateLoan( Loan loan);
	void deleteLoan(int loanid);
	
	Loan getLoanById(int id);
}
