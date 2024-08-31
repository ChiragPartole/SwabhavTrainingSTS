package com.techlabs.jpacrud.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.jpacrud.entity.Student;

public interface StudentService {

	List<Student> getStudents();
	Optional<Student> getByRollNumber(int rollnumber);
	void addStudent(Student student);
	void deleteStudent(int rollnumber);
	
	List<Student> getStudentsByName(String name);
	
}
