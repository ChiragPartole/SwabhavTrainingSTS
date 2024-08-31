package com.techlabs.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.transaction.entity.Address;
import com.techlabs.transaction.entity.Employee;
import com.techlabs.transaction.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private AddressService addressService;
    
    
    @Override
    @Transactional
    public Employee addEmployee(Employee employee) throws Exception {
        Employee employeeSavedToDB = this.employeeRepository.save(employee);
        
        
        Address address = null;
        address.setId(123L);
        address.setAddress("Kalyan");
        address.setEmployee(employee);
        
        // calling addAddress() method 
        // of AddressService class
        this.addressService.addAddress(address);
        return employeeSavedToDB;
    }
}