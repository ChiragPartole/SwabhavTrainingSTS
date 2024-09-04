package com.techlabs.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bankapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	boolean existsByAccountNumber(long accountnumber);
	
}
