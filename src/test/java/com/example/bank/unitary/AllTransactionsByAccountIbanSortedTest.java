package com.example.bank.unitary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bank.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AllTransactionsByAccountIbanSortedTest {
	
	@Autowired
    private TransactionService service;
	
	@Test
    public void test()
    {	
		
		String[] sorted = new String[2];
		sorted[0] = "ASC";
		sorted[1] = "DESC";
		
		String[] dateAccountIban = new String[2];
		dateAccountIban[0] = "ES9820385778983000760236";
		dateAccountIban[1] = "ES9820385888983000760236";		
		
		/*************************************************************************/
		/** Retrieve all records from an iban account sorted by date_transaction */
		/*************************************************************************/
		
		for (int i = 0; i < dateAccountIban.length; i++) 
		{
			for (int k = 0; k < sorted.length; k++) {
				service.findTransactionsByAccountIbanSorted(dateAccountIban[i], sorted[k]);
			}
		}
		
    }
}
