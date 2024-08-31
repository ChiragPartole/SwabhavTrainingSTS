package com.techlabs.cashmgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.dto.SalaryAccountDto;
import com.techlabs.cashmgmt.entity.SalaryAccount;
import com.techlabs.cashmgmt.entity.SalaryTransaction;
import com.techlabs.cashmgmt.repository.SalaryAccountRepository;
import com.techlabs.cashmgmt.repository.SalaryTransactionRepository;

@Service
public class SalaryAccountServiceImpl implements SalaryAccountService{

	@Autowired
	private SalaryAccountRepository salaryAccountRepo;
	
	@Autowired
	private SalaryTransactionRepository salaryTransactionRepo;
	
	@Override
	public PageResponse<SalaryAccount> getAllSalaryAccounts(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<SalaryAccount> salaryAccountPage = salaryAccountRepo.findAll(pageable);
		PageResponse<SalaryAccount> salaryAccountPageResponse = new PageResponse<SalaryAccount>();
		
		salaryAccountPageResponse.setContent(salaryAccountPage.getContent());
		salaryAccountPageResponse.setLastPage(salaryAccountPage.isLast());
		salaryAccountPageResponse.setSize(salaryAccountPage.getSize());
		salaryAccountPageResponse.setTotalElements(salaryAccountPage.getTotalElements());
		salaryAccountPageResponse.setTotalPages(salaryAccountPage.getTotalPages());
		
		return salaryAccountPageResponse;
	}

	
	@Override
	public SalaryAccount addSalaryAccount(SalaryAccountDto salaryAccountDto) {
		SalaryAccount salaryAccount = new SalaryAccount();
		
		salaryAccount.setAccountNumber(salaryAccountDto.getAccountNumber());
		salaryAccount.setAccountHolderName(salaryAccountDto.getAccountHolderName());
		
		return salaryAccountRepo.save(salaryAccount);
	}


	@Override
	public SalaryAccount allocateTransactionToSalaryAccount(long accountNumber, List<Integer> transactionID) {

		Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepo.findById(accountNumber);
		if(!optionalSalaryAccount.isPresent())
			return null;
		
		SalaryAccount salaryAccount = optionalSalaryAccount.get();
		List<SalaryTransaction> salaryTransactionList = new ArrayList<SalaryTransaction>();
		
		for(Integer transaction : transactionID) {
			SalaryTransaction salaryTransaction = salaryTransactionRepo.findById(transaction).get();
			salaryTransaction.setSalaryAccount(salaryAccount);
			salaryTransactionRepo.save(salaryTransaction);
			salaryTransactionList.add(salaryTransaction);
		}
		
		salaryAccount.setSalaryTransactions(salaryTransactionList);
		
		return salaryAccountRepo.save(salaryAccount);
	}

	

}
