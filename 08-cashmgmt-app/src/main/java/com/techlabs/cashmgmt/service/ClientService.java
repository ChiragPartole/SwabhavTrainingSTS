package com.techlabs.cashmgmt.service;

import java.util.List;

import com.techlabs.cashmgmt.dto.ClientDto;
import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Client;

public interface ClientService {

	
	PageResponse<Client> getAllClients(int pageNo,int pageSize);
	void addClient(ClientDto clientDto);
	Client updateClient(Client client);
	void deleteClientById(int clientID);
	
	Client allocateClientToEmployee(int clientID, List<Integer> employeeID);
	
}
