package com.techlabs.mapping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.mapping.entity.Address;
import com.techlabs.mapping.entity.Student;
import com.techlabs.mapping.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepo;

	@Override
	public Student addStundent(Student student) {
		
		return studentRepo.save(student);
	}

	@Override
	public Address updateStudentAddress(int rollnumber, Address address) {
		Student dbStudent=null;
		
		Optional<Student> optionalStudent=studentRepo.findById(rollnumber);
		if(!optionalStudent.isPresent())
			return null;
		dbStudent=optionalStudent.get();
		Address dbAddress=dbStudent.getAddress();
		dbAddress.setCity(address.getCity());
		dbStudent.setAddress(dbAddress);
		
		//dbStudent.setAddress(address);
		return studentRepo.save(dbStudent).getAddress();
		
		
		
	}

}
