package com.techlabs.batch.entity;

import org.hibernate.annotations.Collate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "employees")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

	@Id
	@Column(name = "employeeID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeID;
	@Column(name = "name")
	private String name;
	@Column(name="salary")
	private double salary;
}
