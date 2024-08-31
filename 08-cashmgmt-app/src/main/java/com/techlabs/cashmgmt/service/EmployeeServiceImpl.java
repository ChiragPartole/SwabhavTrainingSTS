package com.techlabs.cashmgmt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.dto.EmployeeDto;
import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Employee;
import com.techlabs.cashmgmt.entity.SalaryAccount;
import com.techlabs.cashmgmt.repository.EmployeeRepository;
import com.techlabs.cashmgmt.repository.SalaryAccountRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private SalaryAccountRepository salaryAccountRepo;
	
	@Override
	public PageResponse<Employee> getAllEmployees(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Employee> employeePage = employeeRepo.findAll(pageable);
		PageResponse<Employee> employeePageResponse = new PageResponse<Employee>();
		employeePageResponse.setContent(employeePage.getContent());
		employeePageResponse.setLastPage(employeePage.isLast());
		employeePageResponse.setSize(employeePage.getSize());
		employeePageResponse.setTotalElements(employeePage.getTotalElements());
		employeePageResponse.setTotalPages(employeePage.getTotalPages());
		
		return employeePageResponse;
	}
	

	@Override
	public void addEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setFirstname(employeeDto.getFirstname());
		employee.setLastname(employeeDto.getLastname());
		employee.setPhonenumber(employeeDto.getPhonenumber());
		employee.setEmail(employeeDto.getEmail());
		employee.setPosition(employeeDto.getPosition());
		employee.setHireDate(employeeDto.getHireDate());
		employee.setSalary(employeeDto.getSalary());
		employee.setBankifscnumber(employeeDto.getBankifscnumber());
		employee.setEmployeeStatus(employeeDto.getEmployeeStatus());
		
		employeeRepo.save(employee);
		
	}

	@Override
	public void deleteEmployee(int employeeID) {
		employeeRepo.deleteById(employeeID);
		
	}


	@Override
	public void updateEmployee(Employee employee) {
		
		
		employeeRepo.save(employee);	
		SalaryAccount existingSalaryAccount = employee.getSalaryAccount();
		existingSalaryAccount.setAccountNumber(employee.getSalaryAccount().getAccountNumber());
		existingSalaryAccount.setAccountHolderName(employee.getSalaryAccount().getAccountHolderName());

	}


	@Override
	public SalaryAccount getSalaryAccountByEmployeeId(int employeeId) {

		Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
		
		if(!optionalEmployee.isPresent())
			return null;
		Employee existingEmployee = optionalEmployee.get();
		
		return existingEmployee.getSalaryAccount();
	}


	@Override
	public Employee allocateSalaryAccountToEmployee(int employeeID, long accountNumber) {
		Optional<Employee> optionalEmployee = employeeRepo.findById(employeeID);
		
		if(!optionalEmployee.isPresent())
			return null;
		
		Employee employee = optionalEmployee.get();
		
		Optional<SalaryAccount> optionalSalaryAccount = salaryAccountRepo.findById(accountNumber);
		if(!optionalSalaryAccount.isPresent())
			return null;
		
		SalaryAccount salaryAccount = optionalSalaryAccount.get();
		employee.setSalaryAccount(salaryAccount);
		salaryAccountRepo.save(salaryAccount);
		
		return employeeRepo.save(employee);
	}
	

	

}
