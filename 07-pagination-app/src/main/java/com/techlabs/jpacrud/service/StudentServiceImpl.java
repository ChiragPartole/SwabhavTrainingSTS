package com.techlabs.jpacrud.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.jpacrud.dto.PageResponse;
import com.techlabs.jpacrud.entity.Student;
import com.techlabs.jpacrud.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public PageResponse<Student> getStudents(int pageNo,int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		
		PageResponse<Student> studentPageResponse = new PageResponse<Student>();
		studentPageResponse.setTotalPages(studentPage.getTotalPages());
		studentPageResponse.setSize(studentPage.getSize());
		studentPageResponse.setTotalElements(studentPage.getTotalElements());
		studentPageResponse.setContent(studentPage.getContent());
		studentPageResponse.setLastPage(studentPage.isLast());
		return studentPageResponse;
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
	public PageResponse<Student>  getStudentsByName(String name,int pageNo,int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Student> studentPage = studentRepo.findByName(name,pageable);
		
		PageResponse<Student> studentPageResponse = new PageResponse<Student>();
		studentPageResponse.setTotalPages(studentPage.getTotalPages());
		studentPageResponse.setSize(studentPage.getSize());
		studentPageResponse.setTotalElements(studentPage.getTotalElements());
		studentPageResponse.setContent(studentPage.getContent());
		studentPageResponse.setLastPage(studentPage.isLast());
		return studentPageResponse;
	}

}
