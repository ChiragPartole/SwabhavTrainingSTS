package com.techlabs.lending.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="loan")
public class Loan {
	
	
	@Column(name="loanID")
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int loanID;
	
	@Positive(message = "loanAmount must be positive")
	@Column(name="loanAmount")
	private double loanAmount;
	
	@Positive(message = "rate of interest must be positive")
	@Column(name="rateofinterest")
	private double rateofinterest;
	
	@Min(0)
	@Column(name="loanterm")
	private int loanTerm;
	
	@NotNull(message = "date cannot be null")
	@Past
	@Column(name="startdate")
	private Date startDate;
	
	@NotNull(message = "date cannot be null")
	@Future
	@Column(name="enddate")
	private Date endDate;
	
	@NotNull
	@Column(name="loanStatus")
	private LoanStatus loanStatus;
	
	public Loan(int loanID, double loanAmount, double rateofinterest, int loanTerm, Date startDate, Date endDate,
			LoanStatus loanStatus) {
		super();
		this.loanID = loanID;
		this.loanAmount = loanAmount;
		this.rateofinterest = rateofinterest;
		this.loanTerm = loanTerm;
		this.startDate = startDate;
		this.endDate = endDate;
		this.loanStatus = loanStatus;
	}
	
	public Loan() {
	}

	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getRateofinterest() {
		return rateofinterest;
	}

	public void setRateofinterest(double rateofinterest) {
		this.rateofinterest = rateofinterest;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

	@Override
	public String toString() {
		return "Loan [loanID=" + loanID + ", loanAmount=" + loanAmount + ", rateofinterest=" + rateofinterest
				+ ", loanTerm=" + loanTerm + ", startDate=" + startDate + ", endDate=" + endDate + ", loanStatus="
				+ loanStatus + "]";
	}
	
	

}
