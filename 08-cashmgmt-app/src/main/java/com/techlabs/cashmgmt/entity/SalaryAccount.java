package com.techlabs.cashmgmt.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="SalaryAccount")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SalaryAccount {

	@Id
	@Column(name="accountNumber") 
	private long accountNumber;
	
	@Column(name="accountHolderNames")
	private String accountHolderName;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name="bankID")
	private Bank bank;
	
	@OneToMany(mappedBy = "salaryAccount",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
	@JsonIgnore
	private List<SalaryTransaction> salaryTransactions;
}
