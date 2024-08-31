package com.techlabs.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;
import com.techlabs.mappings.entity.Student;
import com.techlabs.mappings.service.CourseService;


@RestController
@RequestMapping("/studentsapp")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@PostMapping("/courses")
	public ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto) {
		return ResponseEntity.ok(courseService.addCourse(courseDto));
	}
	
	@PutMapping("/courses/instructors")
	public ResponseEntity<Course> allocateInstructorToCourse(@RequestParam int courseID,
			@RequestBody Instructor instructor)
	{
		return ResponseEntity.ok(courseService.allocateInstructor(courseID,instructor));
	}
	
	@PutMapping("/courses/students")
	public ResponseEntity<CourseDto> assignStudents(@RequestParam int courseID,@RequestBody List<Integer> studentIds){
		return  ResponseEntity.ok(courseService.assignStudents(courseID, studentIds));
	}
}
