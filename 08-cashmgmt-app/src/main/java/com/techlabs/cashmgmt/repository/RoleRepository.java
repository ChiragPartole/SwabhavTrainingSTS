package com.techlabs.cashmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.cashmgmt.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
