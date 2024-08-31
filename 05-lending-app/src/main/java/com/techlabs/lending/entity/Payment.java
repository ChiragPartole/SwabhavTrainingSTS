package com.techlabs.lending.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="payment")
public class Payment {

	@Column(name="paymentID")
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int paymentID;
	
	@NotNull(message = "date cannot be null")
	@Past
	@Column(name="date")
	private Date date;
	
	@Positive
	@Column(name="amount")
	private double amount;
	
	@NotNull(message = "paymentmode cannot be null")
	@Column(name="paymentMode")
	private PaymentMode paymentMode;
	
	@NotNull(message = "payment status cannot be null")
	@Column(name="paymentStatus")
	private PaymentStatus paymentStatus;
	public Payment(int paymentID, Date date, double amount, PaymentMode paymentMode, PaymentStatus paymentStatus) {
		super();
		this.paymentID = paymentID;
		this.date = date;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
	}
	
	public Payment() {
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Payment [paymentID=" + paymentID + ", date=" + date + ", amount=" + amount + ", paymentMode="
				+ paymentMode + ", paymentStatus=" + paymentStatus + "]";
	}
	
	
	
}
