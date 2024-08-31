package com.techlabs.mappings.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.mappings.dto.CourseDto;
import com.techlabs.mappings.dto.InstructorDto;
import com.techlabs.mappings.dto.StudentDto;
import com.techlabs.mappings.entity.Course;
import com.techlabs.mappings.entity.Instructor;
import com.techlabs.mappings.entity.Student;
import com.techlabs.mappings.repository.CourseRepository;
import com.techlabs.mappings.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService{

	@Autowired
	private InstructorRepository instructorRepo;
	@Autowired
	private CourseRepository courseRepo;
	
	@Override
	public InstructorDto addInstructor(InstructorDto instructorDto) {
		Instructor instructor = toInstructorMapper(instructorDto);
		
		instructor =  instructorRepo.save(instructor);
		
		return (toInstructorDtoMapper(instructor));
		
	}

	private Instructor toInstructorMapper(InstructorDto instructorDto) {
		Instructor instructor = new Instructor();
		instructor.setInstructorName(instructorDto.getInstructorName());
		instructor.setEmail(instructorDto.getEmail());
		instructor.setQualification(instructorDto.getQualification());
		return instructor;
	}
	
	private InstructorDto toInstructorDtoMapper(Instructor instructor) {
		InstructorDto instructorDto = new InstructorDto();
		instructorDto.setInstructorID(instructor.getInstructorID());
		instructorDto.setInstructorName(instructor.getInstructorName());
		instructorDto.setEmail(instructor.getEmail());
		instructorDto.setQualification(instructor.getQualification());
		return instructorDto;
	}
	
	@Override
	public Instructor allocateCourses(int instructorID, List<Course> courses) {
		
		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorID);
		if(!optionalInstructor.isPresent())
			return null;
		
		Instructor dbInstructor =  optionalInstructor.get();
		
		List<Course> dbCourse = dbInstructor.getCourses();
		
		courses.forEach((course)->{
			Course temp = courseRepo.findById(course.getCourseID()).get();
			temp.setInstructor(dbInstructor);
			dbCourse.add(temp);
		}
		);
		
		dbInstructor.setCourses(dbCourse);
		return instructorRepo.save(dbInstructor);
	}

	@Override
	public InstructorDto getInstructor(int instructorID) {
		Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorID);
		if(!optionalInstructor.isPresent())
			return null;
		
		Instructor dbInstructor =  optionalInstructor.get();
		
		return toInstructorDtoMapper(dbInstructor);
	}

	@Override
	public List<CourseDto> getInstructorCourses(int instructorID) {
		Instructor instructor = instructorRepo.findById(instructorID).orElseThrow(()->new NullPointerException("no instructor found"));
		List<Course> courseList = instructor.getCourses();
		if(courseList==null)
			return null;
		List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
		
		for(Course course: courseList) {
			CourseDto courseDto =new CourseDto();
			courseDto.setCourseName(course.getCourseName());
			courseDto.setDuration(course.getDuration());
			courseDto.setFees(course.getFees());
			
			courseDtoList.add(courseDto);
			
		}
		
		return courseDtoList;
	}

	@Override
	public Page<InstructorDto> getAllInstructors(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Instructor> instructorPage = instructorRepo.findAll(pageable);
		List<InstructorDto> instructorDtoPage = instructorPage.getContent().stream().map(
				instructor ->{
					
					return toInstructorDtoMapper(instructor);
				}).collect(Collectors.toList());


	return new PageImpl<>(instructorDtoPage,instructorPage.getPageable(),instructorPage.getTotalElements());
		

	}

	@Override
	public Set<Student> getStudentsOfAnInstructor(int instructorID) {
		Instructor instructor = instructorRepo.findById(instructorID).orElseThrow(()->new NullPointerException("no instructor found"));
		List<Course> courseList = instructor.getCourses();
		Set<Student> studentList = new HashSet<>();
		courseList.forEach((course)->{
			
			studentList.addAll(course.getStudents());
		});
		
		
		return studentList;
	}


}
