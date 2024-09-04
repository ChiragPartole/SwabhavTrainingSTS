package com.techlabs.bankapp.service;

import com.techlabs.bankapp.dto.PageResponse;
import com.techlabs.bankapp.dto.LoginDto;
import com.techlabs.bankapp.entity.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {

	PageResponse<User> viewAllUsers(int pageNo,int pageSize);
	LoginDto updateUser(LoginDto userDto);
	void deleteUser(int userID);
	
	
	
	
}
