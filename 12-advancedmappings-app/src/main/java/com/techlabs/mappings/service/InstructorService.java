package com.techlabs.mappings.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.dto.InstructorDto;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;
import com.techlabs.mappings.entity.Student;

public interface InstructorService {

	InstructorDto addInstructor(InstructorDto instructorDto);
	
	InstructorDto getInstructor(int instructorID);
	List<CourseDto> getInstructorCourses(int instructorID);
	Page<InstructorDto> getAllInstructors(int pageNo,int pageSize);

	Instructor allocateCourses(int instructorID, List<Course> courses);

	Set<Student> getStudentsOfAnInstructor(int instructorID);



}
