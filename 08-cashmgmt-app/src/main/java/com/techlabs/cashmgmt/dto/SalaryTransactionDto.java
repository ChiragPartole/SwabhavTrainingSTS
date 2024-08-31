package com.techlabs.cashmgmt.dto;

import java.sql.Date;

import com.techlabs.cashmgmt.entity.TransactionStatus;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SalaryTransactionDto {

	private Date transactionDate;
	private double amount;
	private TransactionStatus transactionStatus;
}
