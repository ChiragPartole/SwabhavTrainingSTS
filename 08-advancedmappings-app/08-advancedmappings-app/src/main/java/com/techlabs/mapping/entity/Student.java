package com.techlabs.mapping.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="students")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Student {
	
	@Column(name="rollnumber")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollnumber;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private int age;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address-id")
	private Address address;

}
