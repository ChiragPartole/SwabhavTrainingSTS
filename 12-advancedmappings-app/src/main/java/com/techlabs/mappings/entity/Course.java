package com.techlabs.mappings.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Course")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Course {

	@Id
	@Column(name="courseID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseID;
	
	@Column(name="courseName")
	private String courseName;
	
	@Column(name="duration")
	private int duration;
	
	@Column(name="fees")
	private double fees;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name="instructorID")
	private Instructor instructor;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "courses")
	private List<Student> students;
}
