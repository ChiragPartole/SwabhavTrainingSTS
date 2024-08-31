package com.techlabs.lending.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.lending.entity.Payment;
import com.techlabs.lending.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepo;

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentRepo.getAllPayments();
	}

	@Override
	public void addPayment(Payment payment) {
		// TODO Auto-generated method stub
		System.out.println("inside service");
		paymentRepo.addPayment(payment);
		
	}

	@Override
	public void updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepo.updatePayment(payment);
		
	}

	@Override
	public void deletePayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepo.deletePayment(payment);
		
	}

	@Override
	public Payment getPaymentById(int id) {
		// TODO Auto-generated method stub
		return paymentRepo.getPaymentById(id);
	}

	

}
