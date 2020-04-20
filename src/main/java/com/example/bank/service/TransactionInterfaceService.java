package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.entity.Transaction;
import com.example.bank.response.JsonStatusResponse;

@Service
public interface TransactionInterfaceService {
	
	/*************************************************/
	/** Retrieve all records from transaction table. */
	/*************************************************/
	List<Transaction> findAllTransactions();
	
	/***********************************************/
	/** Retrieve all records from an iban account. */
	/***********************************************/
	List<Transaction> findTransactionsByAccountIban(String accountIban);
	
	/*************************************************************************/
	/** Retrieve all records from an iban account sorted by date_transaction */
	/*************************************************************************/
	List<Transaction> findTransactionsByAccountIbanSorted(String accountIban, String sorted);
	
	/*****************************/
	/** Transaction Registration */
	/*****************************/
	int saveTransaction(Transaction transaction);
		
	/*****************************************************/
	/** Get the status of the transaction in json format */
	/*****************************************************/
	JsonStatusResponse getTransactionStatus(String reference, String channel);
	
	/*********************************/
	/** Get transaction by Reference */
	/*********************************/
	List<Transaction> findTransactionsByReference(String reference);
	
	/**********************/
	/** Transaction exist */
	/**********************/
	boolean transactionExists(String reference);
	
}