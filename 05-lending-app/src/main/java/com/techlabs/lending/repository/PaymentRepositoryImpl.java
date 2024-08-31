package com.techlabs.lending.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.techlabs.lending.entity.Payment;
import com.techlabs.lending.exceptions.PaymentNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository{

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Payment> getAllPayments() {
		TypedQuery<Payment> query = manager.createQuery("SELECT p from Payment p",Payment.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void addPayment(Payment payment) {
		System.out.println("inisde repo");
		manager.persist(payment);
		
	}

	@Override
	@Transactional
	public void updatePayment(Payment payment) {
		manager.merge(payment);
		
	}

	@Override
	@Transactional
	public void deletePayment(Payment payment) {
		Payment existingPayment = manager.find(Payment.class ,payment.getPaymentID() );
		manager.remove(existingPayment);
	}

	@Override
	public Payment getPaymentById(int id) {
		Payment existingPayment = manager.find(Payment.class, id);
		
		if(existingPayment ==null)
			throw new PaymentNotFoundException();
		return existingPayment;
	}

	



}
