package com.techlabs.bankapp.dto;

import com.techlabs.bankapp.entity.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

	private String userName;
	private String userPassword;
	private Roles role;
}
