package com.techlabs.cashmgmt.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Salary;

import com.techlabs.cashmgmt.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService{

	@Autowired
	private SalaryRepository salaryRepo;
	
	@Override
	public PageResponse<Salary> getSalaryDetails(int pageNo,int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Salary> salaryPage = salaryRepo.findAll(pageable);
		PageResponse<Salary> salaryPageResponse = new PageResponse<Salary>();
		salaryPageResponse.setContent(salaryPage.getContent());
		salaryPageResponse.setLastPage(salaryPage.isLast());
		salaryPageResponse.setSize(salaryPage.getSize());
		salaryPageResponse.setTotalElements(salaryPage.getTotalElements());
		salaryPageResponse.setTotalPages(salaryPage.getTotalPages());
		return salaryPageResponse;
	}

	

	@Override
	public void addSalaryDetails(Salary salary) {
		salary.setNetSalary(salary.getGrossSalary() - salary.getDeductions());
		salaryRepo.save(salary);
		
	}

	@Override
	public void updateSalaryDetails(Salary salary) {
		salary.setNetSalary(salary.getGrossSalary() - salary.getDeductions());
		salaryRepo.save(salary);
//		SalaryTransaction exisitngSalaryTransaction = salary.getSalaryTransaction();
//		exisitngSalaryTransaction.setAmount(salary.getSalaryTransaction().getAmount());
//		exisitngSalaryTransaction.setTransacationDate(salary.getSalaryTransaction().getTransacationDate());
//		exisitngSalaryTransaction.setTransactionStatus(salary.getSalaryTransaction().getTransactionStatus());
//		
	}

	@Override
	public void deleteSalaryDetails(int salaryID) {
		salaryRepo.deleteById(salaryID);
		
	}

//	@Override
//	public SalaryTransaction getSalaryTransactionByID(int id) {
//		Optional<Salary> optionalSalary = salaryRepo.findById(id);
//		if(!optionalSalary.isPresent())
//			return null;
//		return optionalSalary.get().getSalaryTransaction();
//	}
	

}
