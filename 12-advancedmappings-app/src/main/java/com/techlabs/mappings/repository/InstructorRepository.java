package com.techlabs.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.mappings.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>{

}
