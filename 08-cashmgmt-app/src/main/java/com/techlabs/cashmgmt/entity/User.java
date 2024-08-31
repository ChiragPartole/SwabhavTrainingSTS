package com.techlabs.cashmgmt.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

	@Id
	@Column(name="userID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userID;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	
	@ManyToMany
	@JoinTable(name="user-role",joinColumns = @JoinColumn(name="userID"),
				inverseJoinColumns = @JoinColumn(name="roleID"))
	private Set<Role> roles;
}
