package com.techlabs.cashmgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.dto.BankDto;
import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Bank;
import com.techlabs.cashmgmt.entity.SalaryAccount;
import com.techlabs.cashmgmt.repository.BankRepository;
import com.techlabs.cashmgmt.repository.SalaryAccountRepository;

@Service
public class BankServiceImpl implements BankService{

	@Autowired
	private BankRepository bankRepo;
	
	@Autowired
	private SalaryAccountRepository salaryAccountRepo;
	
	@Override
	public PageResponse<Bank> getAllBanks(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Bank> bankPage = bankRepo.findAll(pageable);
		
		PageResponse<Bank> bankPageResponse = new PageResponse<Bank>();
		
		bankPageResponse.setContent(bankPage.getContent());
		bankPageResponse.setLastPage(bankPage.isLast());
		bankPageResponse.setSize(bankPage.getSize());
		bankPageResponse.setTotalElements(bankPage.getTotalElements());
		bankPageResponse.setTotalPages(bankPage.getTotalPages());
		
		return bankPageResponse;
	}
	
	
	@Override
	public Bank addBank(BankDto bankDto) {
		Bank bank = new Bank();
		
		bank.setBankName(bankDto.getBankName());
		bank.setIfscNumber(bankDto.getIfscNumber());
		return bankRepo.save(bank);
	}


	@Override
	public Bank allocateSalaryAccountToBank(int bankID, List<Long> salaryAccountIDs) {
		
		Optional<Bank> optionalBank = bankRepo.findById(bankID);
		if(!optionalBank.isPresent())
			return null;
		
		Bank bank = optionalBank.get();
		List<SalaryAccount> salaryAccountList = new ArrayList<SalaryAccount>();
		
		for(Long salaryAccountID: salaryAccountIDs) {
			SalaryAccount salaryAccount = salaryAccountRepo.findById(salaryAccountID).get();
			salaryAccount.setBank(bank);
			salaryAccountRepo.save(salaryAccount);
			salaryAccountList.add(salaryAccount);
		}
		
		bank.setSalaryAccounts(salaryAccountList);
		return bankRepo.save(bank);
	}

}
