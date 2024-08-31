package com.techlabs.mappings.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;
import com.techlabs.mappings.entity.Student;
import com.techlabs.mappings.repository.CourseRepository;
import com.techlabs.mappings.repository.InstructorRepository;
import com.techlabs.mappings.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private InstructorRepository instructorRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	private Course toCourse(CourseDto courseDto) {
		Course course = new Course();
		course.setCourseName(courseDto.getCourseName());
		course.setDuration(courseDto.getDuration());
		course.setFees(courseDto.getFees());
		return course;
	}
	
	private CourseDto toCourseDto(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseName(course.getCourseName());
		courseDto.setDuration(course.getDuration());
		courseDto.setFees(course.getFees());
		return courseDto;
	}
	@Override
	public Course addCourse(CourseDto courseDto) {
		Course course = toCourse(courseDto);
		
		course = courseRepo.save(course);
		
		logger.info("Course added successfully"+course.getCourseID());
		return course;
	}

	@Override
	public Course allocateInstructor(int courseID, Instructor instructor) {
		
		Optional<Course> optionalCourse = courseRepo.findById(courseID);
		if(!optionalCourse.isPresent()) {
			logger.error("Course not found");
			return null;
		}
		
		Course dbCourse = optionalCourse.get();
		dbCourse.setInstructor(instructorRepo.findById(instructor.getInstructorID()).get());
		
	
		return courseRepo.save(dbCourse);
	}

	@Override
	public CourseDto assignStudents(int courseID, List<Integer> studentIds) {
		Course dbCourse = courseRepo.findById(courseID).orElseThrow(()-> new NullPointerException("Course does not exist"));
		
		List<Student> existingStudents = dbCourse.getStudents();
		if(existingStudents ==null)
			existingStudents = new ArrayList<>();
		
		List<Student> studentsToAdd = new ArrayList<>();
		
		studentIds.forEach((id)-> {
			Student student = studentRepo.findById(id).orElseThrow(()-> new NullPointerException("student not found"));
			
			Set<Course> existingCourse = student.getCourses();
			if(existingCourse ==null)
				existingCourse = new HashSet<>();
			
			existingCourse.add(dbCourse);
			studentsToAdd.add(student);
			
		});
		
		existingStudents.addAll(studentsToAdd);
		dbCourse.setStudents(existingStudents);
		

		return toCourseDto(courseRepo.save(dbCourse));
	}

}
