package com.techlabs.lending.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.techlabs.lending.entity.Loan;

import com.techlabs.lending.exceptions.LoanNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class LoanRepositoryImpl implements LoanRepository{

	
	@Autowired
	private EntityManager manager;
	
	
	@Override
	public List<Loan> getAllLoans() {
		TypedQuery<Loan> query = manager.createQuery("SELECT l from Loan l",Loan.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void addLoan(Loan loan) {
		manager.persist(loan);
		
	}

	@Override
	@Transactional
	public void updateLoan(Loan loan) {
		manager.merge(loan);
		
	}

	@Override
	@Transactional
	public void deleteLoan(int loanid) {
		
		Loan loan = manager.find(Loan.class, loanid);
		if (loan != null) {
			// Remove the entity if it exists
			manager.remove(loan);
			
		}	
	}

	@Override
	public Loan getLoanById(int id) {
		Loan existingLoan = manager.find(Loan.class, id);
		if(existingLoan ==null) {
			throw new LoanNotFoundException();
		}
		
		return existingLoan;
	}
	
}