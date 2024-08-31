package com.techlabs.bankapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.bankapp.dto.PageResponse;
import com.techlabs.bankapp.dto.UserDto;
import com.techlabs.bankapp.entity.Customer;
import com.techlabs.bankapp.entity.Roles;
import com.techlabs.bankapp.entity.User;
import com.techlabs.bankapp.repository.CustomerRepository;
import com.techlabs.bankapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CustomerRepository customerRepo;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserDto toUserDtoMapper(User user) {
		UserDto dto = new UserDto();
		dto.setUserName(user.getUserName());
		dto.setUserPassword(user.getUserPassword());
		dto.setRole(user.getRole());
		return dto;
	}
	
	public User toUserMapper(UserDto dto) {
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setUserPassword(dto.getUserPassword());
		user.setRole(dto.getRole());
		return user;
	}
	
	@Override
	public boolean login(UserDto userDto) {
		String username = userDto.getUserName();
		String password=userDto.getUserPassword();
		Roles role =userDto.getRole();
		if(!validateCredentials(username,password,role ))
			return false;
		
		User user = userRepo.findByUserNameAndUserPasswordAndRole(username, password, role);
		
		logger.info("logged in as:"+user.getUserName());
		return true;
	}
	
	private boolean validateCredentials(String username, String password, Roles role) {
       User user = userRepo.findByUserNameAndUserPasswordAndRole(username, password, role);
       
       if(user ==null)
    	   return false;
       
       return true;
    }
	
	@Override
	public PageResponse<User> viewAllUsers(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<User> userPage = userRepo.findAll(pageable);
		
		PageResponse<User> userPageResponse = new PageResponse<User>();
		
		userPageResponse.setContent(userPage.getContent());
		userPageResponse.setLastPage(userPage.isLast());
		userPageResponse.setSize(userPage.getSize());
		userPageResponse.setTotalElements(userPage.getTotalElements());
		userPageResponse.setTotalPages(userPage.getTotalPages());
		
		return userPageResponse;
	}

	@Override
	public User addUser(UserDto userDto) {
		User user = toUserMapper(userDto);
		
		
		return userRepo.save(user);
	}
	
	@Override
	public void allocateRoleToCustomer(String role, int customerID) {
		Customer customer = customerRepo.findById(customerID)
				.orElseThrow(()->new NullPointerException("no customer found"));
		
		
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		User user = toUserMapper(userDto);
		userRepo.save(user);
		return toUserDtoMapper(user);
	}

	@Override
	public void deleteUser(int userID) {
		userRepo.deleteById(userID);
		
	}




	
	
}
