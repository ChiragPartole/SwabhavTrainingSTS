package com.techlabs.mappings.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="students")
public class Student {
	
	@Id
	@Column(name="rollNumber")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollNumber;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private int age;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="addressID")
	private Address address;
	
	@ManyToMany
	@JoinTable(name="student-course",joinColumns = @JoinColumn(name="rollNumber"),
				inverseJoinColumns = @JoinColumn(name="courseId"))
	@JsonIgnore
	private Set<Course> courses;

}
