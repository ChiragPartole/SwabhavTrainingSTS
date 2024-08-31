package com.techlabs.bankapp.service;

import com.techlabs.bankapp.dto.CustomerDto;
import com.techlabs.bankapp.dto.PageResponse;
import com.techlabs.bankapp.entity.Customer;

import jakarta.servlet.http.HttpSession;

public interface CustomerService {

	PageResponse<CustomerDto> viewAllCustomers(int pageNoValue, int pageSizeValue);
	
	CustomerDto addCustomer(CustomerDto customerDto);
	
	CustomerDto updateCustomer(CustomerDto customerDto,int customerID);
	
	void deleteCustomer(int customerID);

}
