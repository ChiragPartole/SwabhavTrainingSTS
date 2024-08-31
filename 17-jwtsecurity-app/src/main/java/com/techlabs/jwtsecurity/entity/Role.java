package com.techlabs.jwtsecurity.entity;


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
@Table(name = "roles")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Role {
	
	@Id
	@Column(name="roleID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleID;
	
	@Column(name = "rolename")
	private String rolename;

}
