package com.techlabs.bankapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bankapp.entity.Account;
import com.techlabs.bankapp.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
	Optional<Transaction> findBySenderAccount(Account account);
}
