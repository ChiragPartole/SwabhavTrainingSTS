package com.techlabs.lending.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="customer")
public class Customer {

	
	@Column(name="customerID")
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int customerID;
	
	
	@NotNull(message = "first name cannot be null")
	@NotBlank(message = "first name cannot be blank")
	@Column(name="firstname")
	private String firstname;
	
	@NotNull(message = "last name cannot be null")
	@NotBlank(message = "last name cannot be blank")
	@Column(name="lastname")
	private String lastname;
	
	
	@Column(name="dateofbirth")
	private Date dateofbirth;
	
	@Email(message = "email not acceptable")
	@Column(name="email")
	private String email;
	
	@NotNull
	@Min(1) 
	@Column(name="mobilenumber")
	private long mobilenumber;
	
	
	public Customer(int customerID, String firstname, String lastname, Date dateofbirth, String email,
			long mobilenumber) {
		super();
		this.customerID = customerID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dateofbirth = dateofbirth;
		this.email = email;
		this.mobilenumber = mobilenumber;
	}


	public Customer() {
	}


	public int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public Date getDateofbirth() {
		return dateofbirth;
	}


	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public long getMobilenumber() {
		return mobilenumber;
	}


	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}


	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", dateofbirth=" + dateofbirth + ", email=" + email + ", mobilenumber=" + mobilenumber + "]";
	}
	
	
	
}
