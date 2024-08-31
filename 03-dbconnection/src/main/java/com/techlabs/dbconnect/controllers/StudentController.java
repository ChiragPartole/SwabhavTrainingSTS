package com.techlabs.dbconnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.dbconnect.entity.Student;
import com.techlabs.dbconnect.service.StudentServiceImpl;

@RestController
@RequestMapping("/studentsapp")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(){
		return ResponseEntity.ok(studentService.getAllStudents());
	}
	
	@GetMapping("/student/{rollnumber}")
	public ResponseEntity<Student> getStudent(@PathVariable int rollnumber){
		return ResponseEntity.ok(studentService.getStudent(rollnumber));
	}
	
	@PostMapping("/students")
	public String addStudent(@RequestBody Student student) {
		studentService.addStudent(student);
		return "Student added successfully!";
	}
	
	@GetMapping("/students/name")
	public ResponseEntity<List<Student>> getStudentByName(String name){
		return ResponseEntity.ok(studentService.getStudentByName(name));
	}
	
	
}
