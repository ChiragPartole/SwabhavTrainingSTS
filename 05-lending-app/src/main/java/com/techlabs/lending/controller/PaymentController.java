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
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.lending.entity.Payment;
import com.techlabs.lending.service.PaymentService;

@RestController
@RequestMapping("/lendingapp")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/payments")
	public ResponseEntity<List<Payment>> getAllPayments(){
		return ResponseEntity.ok(paymentService.getAllPayments());
	}
	
	@GetMapping("/payments/{paymentID}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable int paymentID){
		return ResponseEntity.ok(paymentService.getPaymentById(paymentID));
	}
	
	@PostMapping("/payments")
	public String addPayment(@RequestBody Payment payment) {
		paymentService.addPayment(payment);
		return "added successfully";
	}
	
	@PostMapping("/updatepayments")
	public String updatePayment(@RequestBody Payment payment) {
		paymentService.updatePayment(payment);
		return "updated successfully";
	}
	
	@DeleteMapping("/deletepayments")
	public String deletePayment(@RequestBody Payment payment) {
		paymentService.deletePayment(payment);
		return "deleted successfully";
	}
}
