package com.techlabs.lending.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.lending.entity.Loan;
import com.techlabs.lending.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService{

	@Autowired
	private LoanRepository loanRepo;
	@Override
	public List<Loan> getAllLoans() {
		// TODO Auto-generated method stub
		return loanRepo.getAllLoans();
	}

	@Override
	public void addLoan(Loan loan) {
		loanRepo.addLoan(loan);
		
	}

	@Override
	public void updateLoan(Loan loan) {
		loanRepo.updateLoan(loan);
		
	}

	@Override
	public void deleteLoan(int loanid) {
		loanRepo.deleteLoan(loanid);
		
	}

	@Override
	public Loan getLoanById(int id) {
		// TODO Auto-generated method stub
		return loanRepo.getLoanById(id);
	}

}
