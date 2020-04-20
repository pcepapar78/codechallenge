package com.example.bank.unitary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bank.entity.Transaction;
import com.example.bank.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewTransactionTest {
	
	@Autowired
    private TransactionService service;
	
	@Test
    public void test()
    {	
				
		String reference = "12345Z";
		String accountIban = "ES9820385888983000760236";
		double amount = 300.25;
		double fee = 30.51;
		String description = "New transaction test";
		
		/*****************************/
		/** Transaction Registration */
		/*****************************/		
		Transaction transaction = new Transaction();		
		transaction.setReference(reference);	
		transaction.setAccountIban(accountIban);
		transaction.setAmount(amount);
		transaction.setFee(fee);
		transaction.setDescription(description);
		service.saveTransaction(transaction);			

		
    }
}
