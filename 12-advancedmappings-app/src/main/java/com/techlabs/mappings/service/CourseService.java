package com.techlabs.mappings.service;

import java.util.List;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;

public interface CourseService {

	Course addCourse(CourseDto courseDto);

	Course allocateInstructor(int courseID, Instructor instructor);

	CourseDto assignStudents(int courseID, List<Integer> studentIds);
}
