package com.techlabs.bankapp.dto;

import com.techlabs.bankapp.entity.TransactionType;

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
public class TransactionDto {

	private double amount;
	private TransactionType transactionType;
	private long senderAccountNumber;
	private long receiverAccountNumber;
}
