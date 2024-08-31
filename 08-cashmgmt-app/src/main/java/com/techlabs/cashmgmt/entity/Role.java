package com.techlabs.cashmgmt.entity;



import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role {

	@Id
	@Column(name="roleID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleID;
	@Column(name="roleName")
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
}
