package com.techlabs.cashmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.cashmgmt.dto.ClientDto;
import com.techlabs.cashmgmt.dto.PageResponse;
import com.techlabs.cashmgmt.entity.Client;
import com.techlabs.cashmgmt.service.ClientService;

@RestController
@RequestMapping("/cashmanagementapp")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("/clients")
	public ResponseEntity<PageResponse<Client>> getAllClients(
	        @RequestParam(required = false) Integer pageNo,
	        @RequestParam(required = false) Integer pageSize) {

	    // Provide default values if parameters are not supplied
	    int pageNoValue = (pageNo != null) ? pageNo : 0; // default page number
	    int pageSizeValue = (pageSize != null) ? pageSize : Integer.MAX_VALUE; // large page size to fetch all clients

	    PageResponse<Client> clientsPage = clientService.getAllClients(pageNoValue, pageSizeValue);
	    return ResponseEntity.ok(clientsPage);
	}
	
	@PostMapping("/clients")
	public String addClients(@RequestBody ClientDto clientDto) {
		clientService.addClient(clientDto);
		return " added successfully!";

	}
	
	@DeleteMapping("/clients")
	public String deleteClient(@RequestParam int clientID) {
		clientService.deleteClientById(clientID);
		return "deleted successfully";
	}
	
	@PostMapping("/clients/employees")
	public ResponseEntity<Client> allocateClientToEmployee(@RequestParam int clientID,@RequestParam List<Integer> employeeID)
	{
		return ResponseEntity.ok(clientService.allocateClientToEmployee(clientID,employeeID));
	}
}
