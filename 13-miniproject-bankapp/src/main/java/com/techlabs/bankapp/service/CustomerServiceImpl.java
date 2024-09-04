package com.techlabs.bankapp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.bankapp.dto.CustomerDto;
import com.techlabs.bankapp.dto.PageResponse;
import com.techlabs.bankapp.dto.RegistrationDto;
import com.techlabs.bankapp.entity.Customer;
import com.techlabs.bankapp.entity.User;
import com.techlabs.bankapp.repository.CustomerRepository;
import com.techlabs.bankapp.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	private Customer toCustomerMapper(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(customerDto.getPassword());
		customer.setKycStatus(customerDto.getKycStatus());
		return customer;
	}
	
	private CustomerDto toCustomerDtoMapper(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPassword(customer.getPassword());
		customerDto.setKycStatus(customer.getKycStatus());
		return customerDto;
	}
	
	@Override
	@Transactional
	public PageResponse<CustomerDto> viewAllCustomers(int pageNoValue, int pageSizeValue) {
		Pageable pageable = PageRequest.of(pageNoValue, pageSizeValue);
		Page<Customer> customerPage = customerRepo.findAll(pageable);
		

		PageResponse<CustomerDto> response = new PageResponse<CustomerDto>();
		
		response.setTotalPages(customerPage.getTotalPages());
		response.setTotalElements(customerPage.getTotalElements());
		response.setSize(customerPage.getSize());
		response.setLastPage(customerPage.isLast());
		
		List<CustomerDto> customerDtoList = new ArrayList<>();
		
		customerPage.getContent().forEach((customer)->{
			customerDtoList.add(toCustomerDtoMapper(customer));
		});
		
		response.setContent(customerDtoList);
		return response;
	}

	@Override
	@Transactional
	public CustomerDto addCustomer(CustomerDto customerDto) {
		

	    Customer customer = new Customer();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(customerDto.getPassword());
	    
	    RegistrationDto registrationDto = new RegistrationDto();
	    System.out.println("register username:" +customerDto.getEmail());
	    System.out.println("register password:" +customerDto.getPassword());
	    registrationDto.setUsername(customerDto.getEmail());
	    registrationDto.setPassword(customerDto.getPassword());
	    registrationDto.setRole("ROLE_CUSTOMER");
	    
	    User user = authService.register(registrationDto);
	   
	    logger.info("added user: "+user.getUserName());
	    allocateUserToCustomer(user,customerDto);
	    
	    
	    return toCustomerDtoMapper(customer);
	}
	
	@Transactional
	private void allocateUserToCustomer(User user, CustomerDto customerDto) {
		Customer customer = toCustomerMapper(customerDto);
		customer.setUser(user);
		customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
		customerRepo.save(customer);
		logger.info("added customer: "+customer.getEmail());
	}

	@Override
	@Transactional
	public CustomerDto updateCustomer(CustomerDto customerDto, int customerID) {
		Customer customer = customerRepo.findById(customerID)
				.orElseThrow(()->new NullPointerException("no customer found"));
		
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));

		customerRepo.save(customer);
		logger.info("updated customer");
		
		User user = customer.getUser();
		user.setUserName(customer.getEmail());
		user.setUserPassword(customer.getPassword());
		userRepo.save(user);
		logger.info("updated user");
		
		return customerDto;
	}



	
	@Override
	@Transactional
	public void deleteCustomer(int customerID) {
		customerRepo.deleteById(customerID);
	}


}
