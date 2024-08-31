package com.techlabs.cashmgmt.service;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Salary;


public interface SalaryService {

	PageResponse<Salary> getSalaryDetails(int pageNo, int pageSize);
	void addSalaryDetails(Salary salary);
	void updateSalaryDetails(Salary salary);
	void deleteSalaryDetails(int salaryID);
	
	
}
