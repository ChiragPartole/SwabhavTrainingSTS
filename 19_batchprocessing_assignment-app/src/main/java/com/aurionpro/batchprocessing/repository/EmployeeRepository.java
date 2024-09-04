package com.aurionpro.batchprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.aurionpro.batchprocessing.entity.Employee;

@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
