package com.techlabs.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.bankapp.entity.Roles;
import com.techlabs.bankapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUserNameAndUserPasswordAndRole(String username,String password, Roles rolename);
}
