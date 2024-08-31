package com.techlabs.jpacrud.controller;

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

import com.techlabs.jpacrud.dto.PageResponse;
import com.techlabs.jpacrud.entity.Student;
import com.techlabs.jpacrud.service.StudentService;


@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<PageResponse<Student>> getStudents(@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize) {
		 // Provide default values if parameters are not supplied
	    int pageNoValue = (pageNo != null) ? pageNo : 0; // default page number
	    int pageSizeValue = (pageSize != null) ? pageSize : Integer.MAX_VALUE; // large page size to fetch all clients

		if(name != null) {
			return ResponseEntity.ok(studentService.getStudentsByName(name,pageNoValue,pageSizeValue));
		}
		return ResponseEntity.ok(studentService.getStudents(pageNoValue,pageSizeValue));
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
