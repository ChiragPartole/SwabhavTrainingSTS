package com.techlabs.mappings.service;

import java.util.List;

import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.dto.StudentDto;
import com.techlabs.mappings.entity.Address;
import com.techlabs.mappings.entity.Student;


public interface StudentService {

	StudentDto addStudent(Student student);

	Address getAddressByRollNumber(int rollnumber);
	Address updateAddressByRollNumber(int rollnumber, String city);
	
	PageResponse<StudentDto> getAllStudents(int pageNo,int pageSize);
	
	StudentDto getStudentByRollNumber(int rollnumber);
	
	StudentDto assignCourses(int rollnumber,List<Integer> courseIds);
}
