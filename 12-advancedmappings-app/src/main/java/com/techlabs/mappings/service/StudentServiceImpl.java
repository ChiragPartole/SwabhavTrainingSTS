package com.techlabs.mappings.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.mappings.dto.PageResponse;
import com.techlabs.mappings.dto.StudentDto;
import com.techlabs.mappings.entity.Address;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Student;
import com.techlabs.mappings.repository.CourseRepository;
import com.techlabs.mappings.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	private StudentDto toStudentDtoMapper(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setAge(student.getAge());
		studentDto.setName(student.getName());
		studentDto.setRollNumber(student.getRollNumber());
		
		return studentDto;
	}
	
	private Student toStudentMapper(StudentDto studentDto) {
		Student student = new Student();
		student.setAge(studentDto.getAge());
		student.setName(studentDto.getName());

		return student;
	}
	@Override
	public StudentDto addStudent(Student student) {
		
		
		Student studentdb =  studentRepo.save(student) ;
		
		return toStudentDtoMapper(studentdb);
	}


	@Override
	public Address getAddressByRollNumber(int rollnumber) {
		// TODO Auto-generated method stub
		Optional<Student> studentOptional =  studentRepo.findById(rollnumber);
		Student existingStudent = studentOptional.get();
		return existingStudent.getAddress();
		
	}


	@Override
	public Address updateAddressByRollNumber(int rollnumber, String city) {

		Optional<Student> studentOptional =  studentRepo.findById(rollnumber);
		
		if(!studentOptional.isPresent())
			return null;
		Student existingStudent = studentOptional.get();
		Address existingAddress = existingStudent.getAddress();
		
		existingAddress.setCity(city);
		studentRepo.save(existingStudent);
		return existingAddress;
	}

	@Override
	public PageResponse<StudentDto> getAllStudents(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		
		PageResponse<StudentDto> response = new PageResponse<StudentDto>();
		
		response.setTotalPages(studentPage.getTotalPages());
		response.setTotalElements(studentPage.getTotalElements());
		response.setSize(studentPage.getSize());
		response.setLastPage(studentPage.isLast());
		
		List<StudentDto> studentDtoList = new ArrayList<>();
		
		studentPage.getContent().forEach((student)->{
			studentDtoList.add(toStudentDtoMapper(student));
		});
		
		response.setContents(studentDtoList);
		return response;
	}

	@Override
	public StudentDto getStudentByRollNumber(int rollnumber) {
		Student student = studentRepo.findById(rollnumber)
									 .orElseThrow(()-> new NullPointerException("Student not found"));
		return toStudentDtoMapper(student);
	}

	@Override
	public StudentDto assignCourses(int rollnumber, List<Integer> courseIds) {
		Student dbStudent = studentRepo.findById(rollnumber) .orElseThrow(()-> new NullPointerException("Student not found"));
		
		Set<Course> existingCourses = dbStudent.getCourses();
		
		if(existingCourses == null)
			existingCourses = new HashSet<>();
		
		Set<Course> coursesToAdd = new HashSet<>();
		
		courseIds.forEach((id)->{
			Course course = courseRepo.findById(id)
									  .orElseThrow(()-> new NullPointerException("course not found"));
			
			List<Student> existingStudents =  course.getStudents();
			
			if(existingStudents ==null)
				existingStudents = new ArrayList<>();
			
//			existingStudents.add(dbStudent);
			coursesToAdd.add(course);
		});
		
		existingCourses.addAll(coursesToAdd);
		dbStudent.setCourses(existingCourses);

		return toStudentDtoMapper(studentRepo.save(dbStudent));
	}

}
