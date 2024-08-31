package com.techlabs.bankapp.entity;

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
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

	@Id
	@Column(name = "userID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userID;
	@Column(name = "userName")
	private String userName;
	@Column(name = "userPassword")
	private String userPassword;
	@Column(name="role")
	private Roles role;
}
