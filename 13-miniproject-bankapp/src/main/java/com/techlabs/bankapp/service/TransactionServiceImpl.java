package com.techlabs.bankapp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.bankapp.dto.PageResponse;
import com.techlabs.bankapp.dto.TransactionDto;
import com.techlabs.bankapp.entity.Account;
import com.techlabs.bankapp.entity.Transaction;
import com.techlabs.bankapp.entity.TransactionType;
import com.techlabs.bankapp.repository.AccountRepository;
import com.techlabs.bankapp.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Override
	public PageResponse<TransactionDto> viewAllTransactions(int pageNo, int pageSize) {
	    Pageable pageable = PageRequest.of(pageNo, pageSize);
	    Page<Transaction> transactionPage = transactionRepo.findAll(pageable);
	    PageResponse<TransactionDto> transactionPageResponse = new PageResponse<>();
	    
	    
	    transactionPageResponse.setLastPage(transactionPage.isLast());
	    transactionPageResponse.setSize(transactionPage.getSize());
	    transactionPageResponse.setTotalElements(transactionPage.getTotalElements());
	    transactionPageResponse.setTotalPages(transactionPage.getTotalPages());

	    List<TransactionDto> transactionDtoList = new ArrayList<>();
	    transactionPage.getContent().forEach((transaction)->{
	    	TransactionDto transactionDto = new TransactionDto();
	    	
	    	transactionDto.setAmount(transaction.getAmount());
	    	transactionDto.setReceiverAccountNumber(transaction.getReceiverAccount().getAccountNumber());
	    	transactionDto.setSenderAccountNumber(transaction.getSenderAccount().getAccountNumber());
	    	transactionDto.setTransactionType(transaction.getTransactionType());
	    	
	    	transactionDtoList.add(transactionDto);
	    });
	    
	    transactionPageResponse.setContent(transactionDtoList);
	    return transactionPageResponse;
	}


	@Override
	public TransactionDto addTransaction(TransactionDto transactionDto) {
		long senderAccNo = transactionDto.getSenderAccountNumber();
		Account senderAccount = accountRepo.findById(senderAccNo)
				.orElseThrow(()->new NullPointerException("no account found"));
		
		long receiverAccNo = transactionDto.getReceiverAccountNumber();
		Account receiverAccount = accountRepo.findById(receiverAccNo)
				.orElseThrow(()->new NullPointerException("no account found"));
		
		
		if(transactionDto.getTransactionType() == TransactionType.CREDIT)
			credit(senderAccount,receiverAccount,transactionDto);
		if(transactionDto.getTransactionType() == TransactionType.DEBIT)
			debit(senderAccount,receiverAccount,transactionDto);
		if(transactionDto.getTransactionType() == TransactionType.TRANSFER)
			transfer(senderAccount,receiverAccount,transactionDto);
		
		
		return transactionDto;	
	}

	private void transfer(Account senderAccount, Account receiverAccount, TransactionDto transactionDto) {
		debit(receiverAccount,senderAccount, transactionDto);
		credit(senderAccount,receiverAccount, transactionDto);
		logger.info("transfer done");
	}


	private void credit(Account senderAccount, Account receiverAccount, TransactionDto transactionDto) {
		Transaction transaction = new Transaction();
	    
	    // Set transaction details
	    transaction.setAmount(transactionDto.getAmount());
	    transaction.setTransactionType(TransactionType.CREDIT);
	    transaction.setSenderAccount(senderAccount);
	    transaction.setReceiverAccount(receiverAccount);

	    // Update receiver's balance
	    double existingBalance = receiverAccount.getBalance();
	    double amount = transactionDto.getAmount();
	    double newBalance = existingBalance + amount;
	    receiverAccount.setBalance(newBalance);

	    // Update sender's transactions
	    List<Transaction> senderTransactions = senderAccount.getSenderAccountTransactions();
	    if (senderTransactions == null) {
	        senderTransactions = new ArrayList<>();
	    }
	    senderTransactions.add(transaction);
	    senderAccount.setSenderAccountTransactions(senderTransactions);

	    // Update receiver's transactions
	    List<Transaction> receiverTransactions = receiverAccount.getReceiverAccountTransactions();
	    if (receiverTransactions == null) {
	        receiverTransactions = new ArrayList<>();
	    }
	    receiverTransactions.add(transaction);
	    receiverAccount.setReceiverAccountTransactions(receiverTransactions);

	      transactionRepo.save(transaction);
	        logger.info("transaction credit saved : "+transaction.getTransactionID());
	        
	        accountRepo.save(senderAccount);
	        logger.info("senderAccount saved: "+senderAccount.getAccountNumber());
	        
	        accountRepo.save(receiverAccount);
	        logger.info("receiver saved: "+receiverAccount.getAccountNumber());
	    
	}
	
	private void debit(Account senderAccount, Account receiverAccount, TransactionDto transactionDto) {
		Transaction transaction = new Transaction();
	    
	    // Set transaction details
	    transaction.setAmount(transactionDto.getAmount());
	    transaction.setTransactionType(TransactionType.DEBIT);
	    transaction.setSenderAccount(senderAccount);
	    transaction.setReceiverAccount(receiverAccount);

	    // Update receiver's balance
	    double existingBalance = receiverAccount.getBalance();
	    double amount = transactionDto.getAmount();
	    double newBalance = existingBalance - amount;
	    receiverAccount.setBalance(newBalance);

	    // Update sender's transactions
	    List<Transaction> senderTransactions = senderAccount.getSenderAccountTransactions();
	    if (senderTransactions == null) {
	        senderTransactions = new ArrayList<>();
	    }
	    senderTransactions.add(transaction);
	    senderAccount.setSenderAccountTransactions(senderTransactions);

	    // Update receiver's transactions
	    List<Transaction> receiverTransactions = receiverAccount.getReceiverAccountTransactions();
	    if (receiverTransactions == null) {
	        receiverTransactions = new ArrayList<>();
	    }
	    receiverTransactions.add(transaction);
	    receiverAccount.setReceiverAccountTransactions(receiverTransactions);

	      transactionRepo.save(transaction);
	        logger.info("transaction debit saved: "+transaction.getTransactionID());
	        accountRepo.save(senderAccount);
	        logger.info("senderAccount saved: "+senderAccount.getAccountNumber());
	        
	        accountRepo.save(receiverAccount);
	        logger.info("receiver saved: "+receiverAccount.getAccountNumber()); 
	    
	}
	
	@Override
	public List<TransactionDto> viewPassbook(long accountNumber) {
		Account dbaccount = accountRepo.findById(accountNumber)
									   .orElseThrow(()->new NullPointerException("no account found"));
		
		
		List<Transaction> transactionList = dbaccount.getSenderAccountTransactions();
		
		List<TransactionDto> transactionDtoList = new ArrayList<>();
		
		transactionList.forEach((transaction)->{
			TransactionDto transactionDto = new TransactionDto();
			
			transactionDto.setAmount(transaction.getAmount());
			transactionDto.setTransactionType(transaction.getTransactionType());
			transactionDto.setSenderAccountNumber(transaction.getSenderAccount().getAccountNumber());
			transactionDto.setReceiverAccountNumber(transaction.getReceiverAccount().getAccountNumber());
			
			transactionDtoList.add(transactionDto);
			
		});
		
		return transactionDtoList;
	}


	
	
	
	
	
	


	@Override
	public void deleteTransaction(int transactionID) {
		transactionRepo.deleteById(transactionID);		
	}











}
