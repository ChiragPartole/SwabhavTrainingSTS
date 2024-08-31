package com.techlabs.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InstructorDto {

	private int instructorID;
	private String instructorName;
	private String email;
	private String qualification;
}
