package com.techlabs.mappings.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Instructor")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Instructor {
	
	@Column(name="instructorID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int instructorID;
	
	@Column(name="instructorName")
	private String instructorName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="qualification")
	private String qualification;
	
	@OneToMany(mappedBy = "instructor",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JsonIgnore
	private List<Course> courses;
}
