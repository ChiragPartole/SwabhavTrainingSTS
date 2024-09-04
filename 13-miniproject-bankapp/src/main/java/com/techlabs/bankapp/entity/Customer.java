package com.techlabs.bankapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

	@Id
	@Column(name="customerID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerID;
	
	@Column(name="firstName")
	 @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabetic characters are allowed")
	 @NotNull(message = "FirstName cannot be null")
	 @NotBlank(message = "FirstName cannot be blank")
	private String firstName;
	
	@Column(name="lastName")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Only alphabetic characters are allowed")
 @NotNull(message = "Lastname cannot be null")
 @NotBlank(message = "lastname cannot be blank")
	private String lastName;
	
	@Column(name="email")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email address")
	private String email;
	
	@Pattern( 
		      regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", 
		      message = "Password must be at least 9 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character." 
		  )
	@Column(name="password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="KycStatus")
	private KycStatus kycStatus;
	
	@OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "userID")
	private User user;
	
	@OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JsonIgnore
	private List<Account> accounts;
	
	@OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JsonIgnore
	private List<Image> images;
}
