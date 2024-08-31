package com.techlabs.lending.service;

import java.util.List;

import com.techlabs.lending.entity.Payment;

public interface PaymentService {

	List<Payment> getAllPayments();
	void addPayment(Payment payment);
	void updatePayment( Payment payment);
	void deletePayment(Payment payment);

	Payment getPaymentById(int id);
}
