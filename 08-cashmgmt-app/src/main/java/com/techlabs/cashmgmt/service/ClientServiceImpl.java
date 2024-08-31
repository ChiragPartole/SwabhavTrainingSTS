package com.techlabs.cashmgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.cashmgmt.dto.ClientDto;
import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Client;
import com.techlabs.cashmgmt.entity.Employee;
import com.techlabs.cashmgmt.repository.ClientRepository;
import com.techlabs.cashmgmt.repository.EmployeeRepository;




@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private EmployeeRepository employeeRepo;
	
	public PageResponse<Client> getAllClients(int pageNo,int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Client> clientPage =  clientRepo.findAll(pageable);
		
		PageResponse<Client> clientPageResponse = new PageResponse<Client>();
		clientPageResponse.setTotalPages(clientPage.getTotalPages());
		clientPageResponse.setSize(clientPage.getSize());
		clientPageResponse.setTotalElements(clientPage.getTotalElements());
		clientPageResponse.setContent(clientPage.getContent());
		clientPageResponse.setLastPage(clientPage.isLast());
		return clientPageResponse;
	}

	@Override
	public void addClient(ClientDto clientDto) {
		Client client = new Client();
		client.setCompanyName(clientDto.getCompanyName());
	    client.setRegistrationNumber(clientDto.getRegistrationNumber());
	    client.setContactPerson(clientDto.getContactPerson());
	    client.setContactEmail(clientDto.getContactEmail());
	    client.setContactNumber(clientDto.getContactNumber());
	    client.setAddress(clientDto.getAddress());
	    client.setStatus(clientDto.getStatus());
	    client.setKycStatus(clientDto.getKycStatus());

	    clientRepo.save(client);	
	}

	@Override
	public void deleteClientById(int clientID) {
		clientRepo.deleteById(clientID);	
	}

	@Override
	public Client updateClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client allocateClientToEmployee(int clientID, List<Integer> employeeID) {
		Optional<Client> optionalClient = clientRepo.findById(clientID);
		
		if(!optionalClient.isPresent())
			return null;
		// check for existing list todo!!
		Client client = optionalClient.get();
		List<Employee> employeesList = new ArrayList<Employee>();  
		
		for(Integer employee :employeeID) {
			Employee employeeDB = employeeRepo.findById(employee).get();
			employeeDB.setClient(client);
			employeeRepo.save(employeeDB);
			employeesList.add(employeeDB);
		}
		
		client.setEmployees(employeesList);
		return clientRepo.save(client);
	}

}
