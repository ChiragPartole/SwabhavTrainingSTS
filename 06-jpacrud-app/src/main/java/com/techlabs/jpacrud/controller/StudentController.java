package com.techlabs.jpacrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.jpacrud.entity.Student;
import com.techlabs.jpacrud.service.StudentService;


@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getStudents(@RequestParam(required = false) String name) {
		
		if(name != null) {
			return ResponseEntity.ok(studentService.getStudentsByName(name));
			
			
		}
		return ResponseEntity.ok(studentService.getStudents());
	}
	
	@PostMapping("/students")
	public String addStudent(@RequestBody Student student) {
		int rollno = student.getRollnumber();
		studentService.addStudent(student);
		if(rollno == 0) {
			
			return "added successfully";
		}else {
			return "updated successfully";
		}
		
	}
	
	@DeleteMapping("/students")
	public String deleteStudent(@RequestParam int rollnumber) {
		studentService.deleteStudent(rollnumber);
		return "deleted successfully";
	}
	
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<Optional<Student>> getStudentByRollnumber(@PathVariable int rollnumber) {
		
		return ResponseEntity.ok(studentService.getByRollNumber(rollnumber));
	}
	
	
}
