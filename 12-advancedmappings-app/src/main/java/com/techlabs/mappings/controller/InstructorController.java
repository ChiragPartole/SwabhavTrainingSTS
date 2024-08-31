package com.techlabs.mappings.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.dto.InstructorDto;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;
import com.techlabs.mappings.entity.Student;
import com.techlabs.mappings.service.InstructorService;

@RestController
@RequestMapping("/studentsapp")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructors")
	public ResponseEntity<InstructorDto> addInstructor(@RequestBody InstructorDto instructorDto){
		return ResponseEntity.ok(instructorService.addInstructor(instructorDto));
	}
	
	@PutMapping("/instructors/courses")
	public ResponseEntity<Instructor> allocateCoursesToInstructor(@RequestParam int instructorID, 
			@RequestBody List<Course> courses){
		return ResponseEntity.ok(instructorService.allocateCourses(instructorID,courses));
	}
	
	@GetMapping("/instructors")
	public ResponseEntity<Page<InstructorDto>> getAllInstructors(@RequestParam int pageNo,
		@RequestParam	int pageSize){
		return ResponseEntity.ok(instructorService.getAllInstructors(pageNo, pageSize));
	}
	
	@GetMapping("/instructors/courses")
	public ResponseEntity<List<CourseDto>> getCoursesOfAnInstructor(@RequestParam int instructorID){
		return ResponseEntity.ok(instructorService.getInstructorCourses(instructorID));
	}
	
	@GetMapping("/instructors/students")
	public ResponseEntity<Set<Student>> getStudentsOfAnInstructor(@RequestParam int instructorID){
		return ResponseEntity.ok(instructorService.getStudentsOfAnInstructor(instructorID));
	}
}
