package com.techlabs.bankapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.bankapp.dto.PageResponse;
import com.techlabs.bankapp.dto.LoginDto;
import com.techlabs.bankapp.entity.Customer;
import com.techlabs.bankapp.entity.User;
import com.techlabs.bankapp.repository.CustomerRepository;
import com.techlabs.bankapp.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CustomerRepository customerRepo;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private LoginDto toUserDtoMapper(User user) {
		LoginDto dto = new LoginDto();
		dto.setUserName(user.getUserName());
		dto.setUserPassword(user.getUserPassword());
		return dto;
	}
	
	public User toUserMapper(LoginDto dto) {
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setUserPassword(dto.getUserPassword());
		
		return user;
	}
	

	@Override
	@Transactional
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
	@Transactional
	public LoginDto updateUser(LoginDto userDto) {
		User user = toUserMapper(userDto);
		userRepo.save(user);
		return toUserDtoMapper(user);
	}

	@Override
	@Transactional
	public void deleteUser(int userID) {
		userRepo.deleteById(userID);
		
	}




	
	
}
