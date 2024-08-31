package com.techlabs.lending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.lending.entity.Loan;
import com.techlabs.lending.service.LoanService;

@RestController
@RequestMapping("/lendingapp")
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	@GetMapping("/loan")
	public ResponseEntity<List<Loan>> getAllLoans(){
		return ResponseEntity.ok(loanService.getAllLoans());
	}
	
	@GetMapping("/loan/{loanID}")
	public ResponseEntity<Loan> getLoanById(@PathVariable int loanID){
		return ResponseEntity.ok(loanService.getLoanById(loanID));
	}
	
	@PostMapping("/loan")
	public String addLoan(@RequestBody Loan loan) {
		loanService.addLoan(loan);
		return "added successfully";
	}
	
	@PostMapping("/updateloan")
	public String updateLoan(@RequestBody Loan loan) {
		loanService.updateLoan(loan);
		return "updated successfully";
	}
	
	@DeleteMapping("/deleteloan")
	public String deleteLoan(@RequestParam int loanID) {
		loanService.deleteLoan(loanID);
		return "deleted successfully";
	}
	
}
