package com.techlabs.cashmgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.dto.SalaryTransactionDto;
import com.techlabs.cashmgmt.entity.Salary;
import com.techlabs.cashmgmt.entity.SalaryAccount;
import com.techlabs.cashmgmt.entity.SalaryTransaction;
import com.techlabs.cashmgmt.repository.SalaryRepository;
import com.techlabs.cashmgmt.repository.SalaryTransactionRepository;

@Service
public class SalaryTransactionServiceImpl implements SalaryTransactionService{

	@Autowired
	private SalaryTransactionRepository salaryTransactionRepo;
	@Autowired
	private SalaryRepository salaryRepo;
	
	@Override
	public PageResponse<SalaryTransaction> getAllSalaryTransactions(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<SalaryTransaction> salaryTransactionPage = salaryTransactionRepo.findAll(pageable);
		PageResponse<SalaryTransaction> salaryTransactionPageResponse = new PageResponse<SalaryTransaction>();
		salaryTransactionPageResponse.setContent(salaryTransactionPage.getContent());
		salaryTransactionPageResponse.setLastPage(salaryTransactionPage.isLast());
		salaryTransactionPageResponse.setSize(salaryTransactionPage.getSize());
		salaryTransactionPageResponse.setTotalElements(salaryTransactionPage.getTotalElements());
		salaryTransactionPageResponse.setTotalPages(salaryTransactionPage.getTotalPages());
		return salaryTransactionPageResponse;
	}

	@Override
	public SalaryTransaction addSalaryTransaction(SalaryTransactionDto salaryTransactionDto) {
		SalaryTransaction salaryTransaction = new SalaryTransaction();
		
		 
	    salaryTransaction.setTransactionDate(salaryTransactionDto.getTransactionDate());
	    salaryTransaction.setAmount(salaryTransactionDto.getAmount());
	    salaryTransaction.setTransactionStatus(salaryTransactionDto.getTransactionStatus());
	    
	    return salaryTransactionRepo.save(salaryTransaction);
	}

	@Override
	public SalaryTransaction allocateSalaryToSalaryTransaction(int transactionID, int salaryID) {
		Optional<SalaryTransaction> optionalSalaryTransaction = salaryTransactionRepo.findById(transactionID);
		
		Optional<Salary> optionalSalary = salaryRepo.findById(salaryID);
		if(!optionalSalaryTransaction.isPresent() || !optionalSalary.isPresent())
			return null;
		
		SalaryTransaction salaryTransaction = optionalSalaryTransaction.get();
		salaryTransaction.setSalary(optionalSalary.get());
		
		salaryRepo.save(optionalSalary.get());
		
		return salaryTransactionRepo.save(salaryTransaction);

	}

}
