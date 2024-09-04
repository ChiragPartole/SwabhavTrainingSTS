package com.techlabs.jwtsecurity.entity;

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
@Table(name = "customers")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

	@Id
	@Column(name="customerID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerID;
	@Column(name="firstName")
	private String firstName;
	@Column(name="lastName")
	private String lastName;
	@Column(name="moblieNumber")
	private long moblieNumber;
}
