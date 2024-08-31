package com.techlabs.cashmgmt.service;

import java.util.List;

import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.dto.RoleDto;

public interface RoleService {

	PageResponse<RoleDto> getAllRoles(int pageNo,int pageSize);
	RoleDto addRole(RoleDto roleDto);
	void deleteRole(int roleID);
	RoleDto assignUsers(int roleID, List<Integer> userIds);
}
