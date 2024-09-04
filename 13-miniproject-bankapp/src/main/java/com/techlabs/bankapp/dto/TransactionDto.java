package com.techlabs.bankapp.dto;

import com.techlabs.bankapp.entity.TransactionType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    private Double amount;

    private TransactionType transactionType;

    @Min(value = 1000000000L, message = "Sender account number must be a 10-digit number")
    @Max(value = 9999999999L, message = "Sender account number must be a 10-digit number")
    private long senderAccountNumber;

    @Min(value = 1000000000L, message = "Reciever account number must be a 10-digit number")
    @Max(value = 9999999999L, message = "Receiver account number must be a 10-digit number")
    private long receiverAccountNumber;
}