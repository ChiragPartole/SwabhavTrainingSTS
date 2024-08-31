package com.techlabs.cashmgmt.service;

import java.util.List;

import com.techlabs.cashmgmt.dto.BankDto;
import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Bank;

public interface BankService {

	PageResponse<Bank> getAllBanks(int pageNo,int pageSize);
	Bank addBank(BankDto bankDto);
	Bank allocateSalaryAccountToBank(int bankID, List<Long> salaryAccountIDs);
}
