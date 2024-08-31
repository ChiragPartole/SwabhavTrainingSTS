package com.techlabs.cashmgmt.entity;



import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="client")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Client {

	@Column(name="clientID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientID;
	
	@Column(name="companyName")
	private String companyName;
	
	@Column(name="registrationNumber")
	private int registrationNumber;
	
	@Column(name="contactPerson")
	private String contactPerson;
	
	@Column(name="contactEmail")
	private String contactEmail;
	
	@Column(name="contactNumber")
	private long contactNumber;
	
	@Column(name="address")
	private String address;
	
	@Column(name="status")
	private ClientStatus status;
	
	
	@Column(nullable = false,name = "creationDate")
	private LocalDate creationDate;
	
	@PrePersist
	private void onCreate() {
		 this.creationDate = LocalDate.now();
	}
	
	@Column(name="kycStatus")
	private KycStatus kycStatus;
	
	@OneToMany(mappedBy = "client",cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JsonIgnore
	private List<Employee> employees;
}
