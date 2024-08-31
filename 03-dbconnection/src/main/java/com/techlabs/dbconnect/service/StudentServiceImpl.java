package com.techlabs.dbconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.dbconnect.entity.Student;
import com.techlabs.dbconnect.repository.StudentRepositoryImpl;



@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepositoryImpl studentRepo;
	
	@Override
	public List<Student> getAllStudents() {
		
		
		return studentRepo.getAllStudents();
	}

	@Override
	public Student getStudent(int rollnumber) {
		// TODO Auto-generated method stub
		return studentRepo.getStudent(rollnumber);
	}

	@Override
	public void addStudent(Student student) {
		studentRepo.addStudent(student);
		
	}

	@Override
	public List<Student> getStudentByName(String name) {
		// TODO Auto-generated method stub
		return studentRepo.getStudentByName(name);
	}

}
