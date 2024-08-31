package com.techlabs.jpacrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.jpacrud.entity.Student;
import com.techlabs.jpacrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public List<Student> getStudents() {
		
		return studentRepo.findAll();
	}

	@Override
	public Optional<Student> getByRollNumber(int rollnumber) {
		return studentRepo.findById(rollnumber);
	}

	@Override
	public void addStudent(Student student) {
		studentRepo.save(student);
		
	}

	@Override
	public void deleteStudent(int rollnumber) {
		studentRepo.deleteById(rollnumber);
		
	}

	@Override
	public List<Student> getStudentsByName(String name) {
		// TODO Auto-generated method stub
		return studentRepo.findByName(name);
	}

}
