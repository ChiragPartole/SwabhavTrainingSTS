package com.techlabs.bankapp.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {

	@Id
	@Column(name="transactionID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionID;
	
	@Column(name="amount")
    @Min(value = 0, message = "Amount cannot be negative")
	@NotNull(message = "Amount is required")
	private double amount;
	
	@Column(name="transactionType")
	@NotNull(message = "Transaction type is required")
	private TransactionType transactionType;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="senderaccountnumber")
	private Account senderAccount;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="receiveraccountnumber")
	private Account receiverAccount;
}
