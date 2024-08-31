package com.techlabs.cashmgmt.dto;

import java.sql.Date;

import com.techlabs.cashmgmt.entity.EmployeeStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmployeeDto {

	private String firstname;
	private String lastname;
	private long phonenumber;
	private String email;
	private String position;
	private Date hireDate;
	private double salary;
	private long bankifscnumber;
	private EmployeeStatus employeeStatus;
}
