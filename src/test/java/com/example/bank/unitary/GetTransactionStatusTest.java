package com.example.bank.unitary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bank.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetTransactionStatusTest {
	
	@Autowired
    private TransactionService service;
	
	@Test
    public void test()
    {	
		/*****************************************************/
		/** Get the status of the transaction in json format */
		/*****************************************************/
		service.getTransactionStatus("999999","ATM");
    }
}
