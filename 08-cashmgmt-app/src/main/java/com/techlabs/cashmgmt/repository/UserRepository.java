package com.techlabs.cashmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.cashmgmt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
