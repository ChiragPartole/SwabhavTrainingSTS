package com.techlabs.cashmgmt.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.dto.UserDto;
import com.techlabs.cashmgmt.entity.Role;
import com.techlabs.cashmgmt.entity.User;
import com.techlabs.cashmgmt.repository.RoleRepository;
import com.techlabs.cashmgmt.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo; 
	
	private UserDto toUserDtoMapper(User user) {
		UserDto userdto = new UserDto();
		userdto.setUserName(user.getUsername());
		userdto.setPassword(user.getPassword());
		return userdto;
	}
	
	private User toUserMapper(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	@Override
	public PageResponse<UserDto> getAllUsers(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<User> userPage = userRepo.findAll(pageable);
		PageResponse<UserDto> response = new PageResponse<UserDto>();
		
		response.setTotalPages(userPage.getTotalPages());
		response.setTotalElements(userPage.getTotalElements());
		response.setSize(userPage.getSize());
		response.setLastPage(userPage.isLast());
		
		List<UserDto> userDtoList = new ArrayList<>();
		
		userPage.getContent().forEach((user)->{
			UserDto userdto = toUserDtoMapper(user);
			
			userDtoList.add(userdto);
		});
		
		response.setContent(userDtoList);
		return response;
	}

	@Override
	public UserDto addUser(UserDto userDto) {
		User user = toUserMapper(userDto);
		user=  userRepo.save(user);
		return toUserDtoMapper(user);
	}

	@Override
	public void deleteUser(int userID) {
		userRepo.deleteById(userID);
	}

	@Override
	public UserDto assignRoles(int userID, List<Integer> roleIds) {
		User userdb = userRepo.findById(userID).orElseThrow(()->new NullPointerException("No user found"));
		
		Set<Role> existingRoles = userdb.getRoles();
		
		if(existingRoles==null)
			existingRoles = new HashSet<>();
		
		Set<Role> rolesToAdd = new HashSet<>();
		
		roleIds.forEach((id)->{
			Role role = roleRepo.findById(id).orElseThrow(()->new NullPointerException("no role found"));
			
			Set<User> existingUsers = role.getUsers();
			if(existingUsers==null)
				existingUsers = new HashSet<>();
			
			existingUsers.add(userdb);
			rolesToAdd.add(role);
			
		});
		existingRoles.addAll(rolesToAdd);
		userdb.setRoles(existingRoles);
		
		return toUserDtoMapper(userRepo.save(userdb));
	}

}
