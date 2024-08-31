package com.techlabs.cashmgmt.entity;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salary")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Salary {

	@Id
	@Column(name="salaryID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int salaryID;
	
	@Column(name="salaryMonth")
	private int salaryMonth;
	
	@Column(name="grossSalary")
	private double grossSalary;
	
	@Column(name="deductions")
	private double deductions;
	
	@Column(name="netSalary")
	private double netSalary;
	
	@Column(name="paymentDate")
	private Date paymentDate;
	
	@Column(name="status")
	private SalaryStatus status;
	

}
