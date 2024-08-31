package com.techlabs.cashmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.dto.UserDto;
import com.techlabs.cashmgmt.service.UserService;

@RestController
@RequestMapping("/cashmanagementapp")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<PageResponse<UserDto>> getAllUsers(int pageNo,int pageSize){
		return  ResponseEntity.ok(userService.getAllUsers(pageNo, pageSize));
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
		
		return ResponseEntity.ok(userService.addUser(userDto));
	}
	
	@PutMapping("/users/roles")
	public ResponseEntity<UserDto> assignRoles(@RequestParam int userID,@RequestBody List<Integer> roleIds){
		return  ResponseEntity.ok(userService.assignRoles(userID, roleIds));
	}
}
