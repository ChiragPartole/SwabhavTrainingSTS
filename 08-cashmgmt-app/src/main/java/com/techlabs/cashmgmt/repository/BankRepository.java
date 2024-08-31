package com.techlabs.cashmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.cashmgmt.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{

}
