package com.techlabs.mappings.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Table(name="address")
public class Address {
	
	@Id
	@Column(name="addressID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressID;
	@Column(name="buildingName")
	private String buildingName;
	@Column(name="city")
	private String city;
	@Column(name="pincode")
	private int pincode;
	

}
