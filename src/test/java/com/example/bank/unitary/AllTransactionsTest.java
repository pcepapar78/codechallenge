package com.example.bank.unitary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bank.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AllTransactionsTest {
	
	@Autowired
    private TransactionService service;
	
	@Test
    public void test()
    {	
		/*************************************************/
		/** Retrieve all records from transaction table. */
		/*************************************************/
		service.findAllTransactions();
    }
}
