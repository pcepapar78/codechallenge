package com.example.bank.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bank.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BussinesRulesTest {
	
	@Autowired
    private TransactionService service;
	
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(TransactionService.class);
	
	@Test
    public void test()
    {	
		// A transaction that is not stored in our system
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[A] - The system returns the status 'INVALID' - CHANNEL: CLIENT");
		service.getTransactionStatus("54321A","CLIENT");
		
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[A] - The system returns the status 'INVALID' - CHANNEL: ATM");
		service.getTransactionStatus("54321A","ATM");
		
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[A] - The system returns the status 'INVALID' - CHANNEL: INTERNAL");
		service.getTransactionStatus("54321A","INTERNAL");
		
		//  A transaction that is stored in our system and the transaction date is before today
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[B] - The system returns the status 'SETTLED' and the amount substracting the fee - CHANNEL: CLIENT");
		service.getTransactionStatus("12345G","CLIENT");
		
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[B] - The system returns the status 'SETTLED' and the amount substracting the fee - CHANNEL: ATM");
		service.getTransactionStatus("12345G","ATM");
		
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[C] - The system returns the status 'SETTLED' and The system returns the amount and the fee - CHANNEL: INTERNAL");
		service.getTransactionStatus("12345G","INTERNAL");
		
		//  A transaction that is stored in our system and the transaction date is equals to today
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[D] - The system returns the status 'PENDING' and the amount substracting the fee - CHANNEL: CLIENT");
		service.getTransactionStatus("12345F","CLIENT");
		
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[D] - The system returns the status 'PENDING' and the amount substracting the fee - CHANNEL: ATM");
		service.getTransactionStatus("12345F","ATM");
		
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[E] - The system returns the status 'PENDING' and The system returns the amount and the fee - CHANNEL: INTERNAL");
		service.getTransactionStatus("12345F","INTERNAL");		
		
		//  A transaction that is stored in our system and the transaction date is greater than today
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[F] - The system returns the status 'FUTURE' and the amount substracting the fee - CHANNEL: CLIENT");
		service.getTransactionStatus("12345D","CLIENT");
		
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[G] - The system returns the status 'PENDING' and the amount substracting the fee - CHANNEL: ATM");
		service.getTransactionStatus("12345D","ATM");
		
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		log.info("Business Rules[H] - The system returns the status 'FUTURE' and The system returns the amount and the fee - CHANNEL: INTERNAL");
		service.getTransactionStatus("12345D","INTERNAL");
		
    }
}
