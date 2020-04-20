package com.example.bank.controller;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.entity.Transaction;
import com.example.bank.response.GeneralResponse;
import com.example.bank.response.JsonStatusResponse;
import com.example.bank.response.TransactionResponse;
import com.example.bank.service.TransactionInterfaceService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin( origins = "*", maxAge = 3600 )
@RestController
@RequestMapping( "/transactions" )
@Scope(value="prototype")
public class TransactionController {
	
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(TransactionController.class);
	
	/*****************************/
	/** The Transaction service. */
	/*****************************/
	@Autowired
	private TransactionInterfaceService transactionService;
	
	/*************************************************/
	/** Retrieve all records from transaction table. */
	/*************************************************/
	@ApiOperation(value = "/find", nickname = "findAllTransactions", notes = "All transactions")
	@ApiResponses(value = { 
			@ApiResponse(code = HttpStatus.SC_OK, message = GeneralResponse.OK),
			@ApiResponse(code = HttpStatus.SC_FORBIDDEN, message = GeneralResponse.FORBIDDEN),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = GeneralResponse.INTERNAL_FAILURE) })
	@RequestMapping(method = RequestMethod.GET, value = "/transactions", produces = { "application/json" })
    public List<Transaction> findAllTransactions() 
	{
	
        return transactionService.findAllTransactions();
    
	}	
	
	/***********************************************/
	/** Retrieve all records from an iban account. */
	/***********************************************/
	@ApiOperation(value = "/find", nickname = "findTransactionsByAccountIban", notes = "Search transactions by Account IBAN")
	@ApiResponses(value = { 
			@ApiResponse(code = HttpStatus.SC_OK, message = GeneralResponse.OK),
			@ApiResponse(code = HttpStatus.SC_FORBIDDEN, message = GeneralResponse.FORBIDDEN),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = GeneralResponse.INTERNAL_FAILURE) })
	@RequestMapping(method = RequestMethod.GET, value = "/transactionsByAccountIban",  produces = { "application/json" } )
    public List<Transaction> findTransactionsByAccountIban(
    		@RequestParam(required = true) String accountIban
    		)
	{		

        return transactionService.findTransactionsByAccountIban(accountIban);
        
    }	
	
	/*************************************************************************/
	/** Retrieve all records from an iban account sorted by date_transaction */
	/*************************************************************************/
	@ApiOperation(value = "/find", nickname = "findTransactionsByAccountIbanSorted", notes = "Search transactions by Account IBAN")
	@ApiResponses(value = { 
			@ApiResponse(code = HttpStatus.SC_OK, message = GeneralResponse.OK),
			@ApiResponse(code = HttpStatus.SC_FORBIDDEN, message = GeneralResponse.FORBIDDEN),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = GeneralResponse.INTERNAL_FAILURE) })
	@RequestMapping(method = RequestMethod.GET, value = "/transactionsByAccountIbanSorted",  produces = { "application/json" } )
    public List<Transaction> findTransactionsByAccountIbanSorted(
    		@RequestParam(required = true) String accountIban,
    		@RequestParam(required = false, defaultValue = "ASC") String sorted
    		)
	{		

        return transactionService.findTransactionsByAccountIbanSorted(accountIban, sorted);
        
    }	
	
	/*****************************/
	/** Transaction Registration */
	/*****************************/
	@ApiOperation(value = "/save", nickname = "createTransaction", notes = "New transaction")
	@ApiResponses(value = { //
			@ApiResponse(code = HttpStatus.SC_OK, message = GeneralResponse.OK),
			@ApiResponse(code = HttpStatus.SC_FORBIDDEN, message = GeneralResponse.FORBIDDEN),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = GeneralResponse.INTERNAL_FAILURE) })
	@RequestMapping(method = RequestMethod.POST, value = "/newTransaction", produces = { "application/json" })
	public ResponseEntity<TransactionResponse> createTransaction(
			@RequestParam(required = true) String reference,
			@RequestParam(required = true) String accountIban,
			@RequestParam(required = true, defaultValue = "0") double amount,
			@RequestParam(required = false, defaultValue = "0") double fee,
			@RequestParam(required = false) String description
			)
	{
		
		int responseHTTPCode = HttpStatus.SC_OK;
		int responseInsert = 0;
		String responseCode = "0";
		
		TransactionResponse transactionResponse = new TransactionResponse(GeneralResponse.OK);
		
		try {
			
			Transaction transaction = new Transaction();
			transaction.setReference(reference);
			transaction.setAccountIban(accountIban);
			transaction.setAmount(amount);
			transaction.setFee(fee);
			transaction.setDescription(description);
			responseInsert = transactionService.saveTransaction(transaction);			
			responseCode = String.valueOf(responseInsert);
					
			transactionResponse.setResponseCode(responseCode);
			
		} catch (Exception e) {
			
			responseCode = "1";
			transactionResponse.setResponseCode(responseCode);
			log.error(e);
			
		}
		return ResponseEntity.status(responseHTTPCode).body(transactionResponse);
	}	
	
	/**************************************/
	/** Get the status of the transaction */
	/**************************************/
	@ApiOperation(value = "/status", nickname = "getTransactionsStatus", notes = "Transaction status")
	@ApiResponses(value = { 
			@ApiResponse(code = HttpStatus.SC_OK, message = GeneralResponse.OK, response = Integer.class),
			@ApiResponse(code = HttpStatus.SC_FORBIDDEN, message = GeneralResponse.FORBIDDEN),
			@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = GeneralResponse.INTERNAL_FAILURE) })
	@RequestMapping(method = RequestMethod.GET, value = "/transactionsStatus",  produces = { "application/json" } )
	public ResponseEntity<JsonStatusResponse>
		getTransactionsStatus(
				@RequestParam(required = true) String reference, 
				@RequestParam(required = false, defaultValue = "CLIENT") String channel
				) 
	{	
		
		JsonStatusResponse json = new JsonStatusResponse();	

    	json = transactionService.getTransactionStatus (reference, channel);
		
		return ResponseEntity.status( HttpStatus.SC_OK )
				.body( json );
		
    }
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<String> noTransactionFound(EmptyResultDataAccessException e) {

		return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No transaction found");
	}
		
}
