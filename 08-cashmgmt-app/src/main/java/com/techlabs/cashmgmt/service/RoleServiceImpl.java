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
import com.techlabs.cashmgmt.dto.RoleDto;
import com.techlabs.cashmgmt.entity.Role;
import com.techlabs.cashmgmt.entity.User;
import com.techlabs.cashmgmt.repository.RoleRepository;
import com.techlabs.cashmgmt.repository.UserRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRepository userRepo;
	@Override
	public PageResponse<RoleDto> getAllRoles(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Role> rolePage = roleRepo.findAll(pageable);
		PageResponse<RoleDto> response = new PageResponse<RoleDto>();
		
		response.setTotalPages(rolePage.getTotalPages());
		response.setTotalElements(rolePage.getTotalElements());
		response.setSize(rolePage.getSize());
		response.setLastPage(rolePage.isLast());
		
		List<RoleDto> roleDtoList = new ArrayList<>();
		
		rolePage.getContent().forEach((role)->{
			RoleDto roleDto = new RoleDto();
			roleDto.setRoleName(role.getRoleName());
			
			roleDtoList.add(roleDto);
		});
		
		response.setContent(roleDtoList);
		return response;
	}
	@Override
	public RoleDto addRole(RoleDto roleDto) {
		Role role = new Role();
		role.setRoleName(roleDto.getRoleName());
		roleRepo.save(role);
		return roleDto;
	}

	@Override
	public void deleteRole(int roleID) {
		roleRepo.deleteById(roleID);
	}
	@Override
	public RoleDto assignUsers(int roleID, List<Integer> userIds) {
		Role roledb = roleRepo.findById(roleID).orElseThrow(()->new NullPointerException("no role found"));
		
		Set<User> existingUsers = roledb.getUsers();
		if(existingUsers==null)
			existingUsers = new HashSet<>();
		
		Set<User> usersToAdd = new HashSet<>();
		
		userIds.forEach((id)->{
			User user = userRepo.findById(id).orElseThrow(()-> new NullPointerException("nouserfound"));
			
			Set<Role> existingRoles = user.getRoles();
			if(existingRoles ==null)
				existingRoles = new HashSet<>();
			
			existingRoles.add(roledb);
			usersToAdd.add(user);
		});
		
		existingUsers.addAll(usersToAdd);
		roledb.setUsers(existingUsers);
		
		roleRepo.save(roledb);
		RoleDto roleDto = new RoleDto();
		roleDto.setRoleName(roledb.getRoleName());
		
		return roleDto;
	}

}

