package com.techlabs.jpacrud.service;



import java.util.Optional;


import com.techlabs.jpacrud.dto.PageResponse;
import com.techlabs.jpacrud.entity.Student;

public interface StudentService {

	PageResponse<Student> getStudents(int pageNo,int pageSize);
	Optional<Student> getByRollNumber(int rollnumber);
	void addStudent(Student student);
	void deleteStudent(int rollnumber);
	
	PageResponse<Student> getStudentsByName(String name,int pageNo,int pageSize);
	
}
