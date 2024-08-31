package com.techlabs.mappings.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.mappings.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	
}
