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
@Table(name = "SalaryTransaction")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SalaryTransaction {

	@Id
	@Column(name="transactionID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionID;
	
	@Column(name="transactionDate")
	private Date transactionDate;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="transactionStatus")
	private TransactionStatus transactionStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "salaryID")
	private Salary salary;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name="accountNumber")
	private SalaryAccount salaryAccount;
}
