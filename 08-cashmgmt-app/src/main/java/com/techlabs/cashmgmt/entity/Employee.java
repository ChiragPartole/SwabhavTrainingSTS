package com.techlabs.cashmgmt.entity;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee {
	
	@Column(name="employeeID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeID;

	@Column(name="firstname")
	private String firstname;

	@Column(name="lastname")
	private String lastname;

	@Column(name="phonenumber")
	private long phonenumber;

	@Column(name="email")
	private String email;
	

	@Column(name="position")
	private String position;

	@Column(name="hireDate")
	private Date hireDate;

	@Column(name="salary")
	private double salary;

	@Column(name="bankifscnumber")
	private long bankifscnumber;

	@Column(name="employeeStatus")
	private EmployeeStatus employeeStatus;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="accountNumber")
	private SalaryAccount salaryAccount;

	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="clientID")
	private Client client;
}
