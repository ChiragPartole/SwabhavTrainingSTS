package com.techlabs.cashmgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SalaryAccountDto {

	private long accountNumber;
	private String accountHolderName;
}
