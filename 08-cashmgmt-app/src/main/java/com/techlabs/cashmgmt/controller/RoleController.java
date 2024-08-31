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
import com.techlabs.cashmgmt.dto.RoleDto;
import com.techlabs.cashmgmt.dto.UserDto;
import com.techlabs.cashmgmt.service.RoleService;

@RestController
@RequestMapping("/cashmanagementapp")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("/roles")
	public ResponseEntity<PageResponse<RoleDto>> getAllUsers(int pageNo,int pageSize){
		return  ResponseEntity.ok(roleService.getAllRoles(pageNo, pageSize));
	}
	
	@PostMapping("/roles")
	public ResponseEntity<RoleDto> addUser(@RequestBody RoleDto roleDto) {
		
		return ResponseEntity.ok(roleService.addRole(roleDto));
	}
	
	@PutMapping("/roles/users")
	public ResponseEntity<RoleDto> assignRoles(@RequestParam int roleID,@RequestBody List<Integer> userIds){
		return  ResponseEntity.ok(roleService.assignUsers(roleID, userIds));
	}
}

