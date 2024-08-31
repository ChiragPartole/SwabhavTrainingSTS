package com.techlabs.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.mappings.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
