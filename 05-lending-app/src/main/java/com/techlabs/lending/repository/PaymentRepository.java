package com.techlabs.lending.repository;

import java.util.List;

import com.techlabs.lending.entity.Payment;

public interface PaymentRepository {

	List<Payment> getAllPayments();
	void addPayment(Payment payment);
	void updatePayment( Payment payment);
	void deletePayment(Payment payment);
	
	Payment getPaymentById(int id);
}