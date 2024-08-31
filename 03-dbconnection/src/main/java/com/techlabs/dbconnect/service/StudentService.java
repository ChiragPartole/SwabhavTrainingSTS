package com.techlabs.dbconnect.service;

import java.util.List;

import com.techlabs.dbconnect.entity.Student;

public interface StudentService {

	 List<Student> getAllStudents();
	 Student getStudent(int rollnumber);
	 void addStudent(Student student);
	 List<Student> getStudentByName(String name);
}
