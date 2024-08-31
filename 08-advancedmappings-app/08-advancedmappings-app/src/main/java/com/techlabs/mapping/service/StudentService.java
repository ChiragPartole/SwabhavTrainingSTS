package com.techlabs.mapping.service;

import com.techlabs.mapping.entity.Address;
import com.techlabs.mapping.entity.Student;

public interface StudentService {
	
	Student addStundent(Student student);

	Address updateStudentAddress(int rollnumber, Address address);

}
