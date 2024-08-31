package com.techlabs.cashmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.cashmgmt.entity.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer>{

}
