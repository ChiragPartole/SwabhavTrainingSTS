package com.techlabs.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.dto.StudentDto;
import com.techlabs.mappings.entity.Address;
import com.techlabs.mappings.entity.Student;
import com.techlabs.mappings.service.StudentService;



@RestController
@RequestMapping("/studentsapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<PageResponse<StudentDto>> getAllStudents(int pageNo,int pageSize){
		return  ResponseEntity.ok(studentService.getAllStudents(pageNo, pageSize));
	}
	
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<StudentDto> getAllStudents(@PathVariable int rollnumber){
		return  ResponseEntity.ok(studentService.getStudentByRollNumber(rollnumber));
	}
	
	@PostMapping("/students")
	public ResponseEntity<StudentDto> addStudent(@RequestBody Student student) {
		
		return ResponseEntity.ok(studentService.addStudent(student));
	}
	
	@GetMapping("/address/{rollnumber}")
	public ResponseEntity<Address> getAllAddressByRollnumber(@PathVariable int rollnumber){
		return  ResponseEntity.ok(studentService.getAddressByRollNumber(rollnumber));
	}
	
	@PutMapping("/students")
	public ResponseEntity<Address> updateAddressByRollnumber(@RequestParam int rollnumber,@RequestParam String city){
		return  ResponseEntity.ok(studentService.updateAddressByRollNumber(rollnumber, city));
	}
	
	@PutMapping("/students/courses")
	public ResponseEntity<StudentDto> assignCourses(@RequestParam int rollnumber,@RequestBody List<Integer> courseIds){
		return  ResponseEntity.ok(studentService.assignCourses(rollnumber, courseIds));
	}
}
