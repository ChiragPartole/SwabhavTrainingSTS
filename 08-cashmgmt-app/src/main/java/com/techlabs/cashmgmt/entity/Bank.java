package com.techlabs.cashmgmt.entity;

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
@Table(name = "bank")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Bank {

	@Id
	@Column(name = "bankID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankID;
	
	@Column(name = "bankName")
	private String bankName;
	
	@Column(name="ifscNumber")
	private long ifscNumber;
	
	@OneToMany(mappedBy = "bank",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JsonIgnore
	private List<SalaryAccount> salaryAccounts;
}
