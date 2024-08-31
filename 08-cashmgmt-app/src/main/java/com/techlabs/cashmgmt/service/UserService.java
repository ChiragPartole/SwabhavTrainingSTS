package com.techlabs.cashmgmt.service;

import java.util.List;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.dto.UserDto;

public interface UserService {

	PageResponse<UserDto> getAllUsers(int pageNo,int pageSize);
	UserDto addUser(UserDto userDto);
	void deleteUser(int userID);
	
	UserDto assignRoles(int userID, List<Integer> roleIds);
}
