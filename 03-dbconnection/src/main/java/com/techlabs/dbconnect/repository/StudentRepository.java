package com.techlabs.dbconnect.repository;

import java.util.List;

import com.techlabs.dbconnect.entity.Student;


public interface StudentRepository {

	List<Student> getAllStudents();
	Student getStudent(int rollnumber);
	void addStudent(Student student);
	List<Student> getStudentByName(String name);
}
