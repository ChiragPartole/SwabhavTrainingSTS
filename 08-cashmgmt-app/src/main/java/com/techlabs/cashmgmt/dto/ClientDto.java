package com.techlabs.cashmgmt.dto;

import com.techlabs.cashmgmt.entity.ClientStatus;
import com.techlabs.cashmgmt.entity.KycStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ClientDto {

	private String companyName;
	private int registrationNumber;
	private String contactPerson;
	private String contactEmail;
	private long contactNumber;
	private String address;
	private ClientStatus status;
	private KycStatus kycStatus;
}
