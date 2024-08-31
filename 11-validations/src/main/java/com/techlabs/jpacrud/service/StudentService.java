package com.techlabs.jpacrud.service;





import com.techlabs.jpacrud.dto.PageResponse;
import com.techlabs.jpacrud.entity.Student;

public interface StudentService {

	PageResponse<Student> getStudents(int pageNo,int pageSize);
	Student getByRollNumber(int rollnumber);
	void addStudent(Student student);
	void deleteStudent(int rollnumber);
	
	PageResponse<Student> getStudentsByName(String name,int pageNo,int pageSize);
	
}
