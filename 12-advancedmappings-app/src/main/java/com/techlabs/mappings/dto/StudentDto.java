package com.techlabs.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentDto {

	private int rollNumber;
	private String name;
	private int age;

}
