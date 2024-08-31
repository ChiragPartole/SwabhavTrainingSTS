package com.techlabs.bankapp.service;

import com.techlabs.bankapp.dto.PageResponse;
import com.techlabs.bankapp.dto.UserDto;
import com.techlabs.bankapp.entity.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {

	PageResponse<User> viewAllUsers(int pageNo,int pageSize);
	UserDto updateUser(UserDto userDto);
	void deleteUser(int userID);
	
	
	User addUser(UserDto userDto);
	
	void allocateRoleToCustomer(String role,int customerID);
	
	boolean login(UserDto userDto);

}
