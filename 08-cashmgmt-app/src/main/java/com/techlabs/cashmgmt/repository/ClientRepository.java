package com.techlabs.cashmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.cashmgmt.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
	
}
