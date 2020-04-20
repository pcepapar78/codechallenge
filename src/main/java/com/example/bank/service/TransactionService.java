package com.example.bank.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bank.entity.Transaction;
import com.example.bank.response.JsonStatusResponse;

@Service
public class TransactionService implements TransactionInterfaceService {
	
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(TransactionService.class);
	
	@Autowired
    private JdbcTemplate jtm;
	
	/*****************************/
	/** The Transaction service. */
	/*****************************/
	@Autowired
	private TransactionInterfaceService transactionService;
	
	/*************************************************/
	/** Retrieve all records from transaction table. */
	/*************************************************/
	@Override
	public List<Transaction> findAllTransactions() 
	{
		
        String sqlSelect = "SELECT * FROM transactions";
        String sqlCount	 = "SELECT COUNT(*) FROM transactions";
        
        List<Transaction> resultQuery = null;
        resultQuery = jtm.query(sqlSelect, new BeanPropertyRowMapper<>(Transaction.class));

        if (resultQuery == null) {
        	log.info("Transactions is null");
        } else {
        	if (TransactionCount(sqlCount, "all", null) == 0) {
        		log.info("No transactions found");
        	}
        }
        
        log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
    	log.info("All transactions:");
    	log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Transaction transaction : resultQuery) {
        	log.info(transaction.toString());
        }
       	log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");

        return resultQuery;
	}
	
	/***********************************************/
	/** Retrieve all records from an iban account. */
	/***********************************************/
	@Override
	public List<Transaction> findTransactionsByAccountIban(String accountIban) 
	{
			
		String sqlSelect = "SELECT * FROM transactions WHERE account_iban = ?";
        String sqlCount	 = "SELECT COUNT(*) FROM transactions WHERE account_iban = ?";
        
        List<Transaction> resultQuery = null;
        resultQuery = jtm.query(sqlSelect, new Object[] { accountIban }, new TransactionRowMapper());

        if (resultQuery == null) {
        	log.info("Transactions is null");
        } else {
        	if (TransactionCount(sqlCount, "account_iban", accountIban) == 0) {
        		log.info("No transactions found");
        	}
        }
        
        log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
    	log.info("All transactions by Account IBAN: [" +accountIban + "]");
    	log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Transaction transaction : resultQuery) {
        	log.info(transaction.toString());
        }
       	log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        return resultQuery;
	}	
	
	
	/*************************************************************************/
	/** Retrieve all records from an iban account sorted by date_transaction */
	/*************************************************************************/
	@Override
	public List<Transaction> findTransactionsByAccountIbanSorted(String accountIban, String sorted) 
	{
		
		 String sqlSelect;
		if (sorted.equals("ASC")) {
			sqlSelect = "SELECT * FROM transactions WHERE account_iban = ? order by amount ASC";
		} else {
			sqlSelect = "SELECT * FROM transactions WHERE account_iban = ? order by amount DESC";
		}
		
		String sqlCount	 = "SELECT COUNT(*) FROM transactions WHERE account_iban = ?";
        
        List<Transaction> resultQuery = null;
       
		resultQuery = jtm.query(sqlSelect, new Object[] { accountIban }, new TransactionRowMapper());

        if (resultQuery == null) {
        	log.info("Transactions is null");
        } else {
        	if (TransactionCount(sqlCount, "account_iban", accountIban) == 0) {
        		log.info("No transactions found");
        	}
        }
        
        log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
    	log.info("All transactions by Account IBAN: [" +accountIban + "] sorted by amount [" + sorted + "]" );
    	log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Transaction transaction : resultQuery) {
        	log.info(transaction.toString());
        }
       	log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        return resultQuery;
	}	
	
	/*****************************/
	/** Transaction Registration */
	/*****************************/
	@Override
	@Transactional
	public int saveTransaction(Transaction transaction) {
		
		String sqlInsert = "INSERT INTO TRANSACTIONS (reference, account_iban, date_transaction, amount, fee, description) "+
		"VALUES (?,?,?,?,?,?)";
		
		int resultQuery = 0;
		
	    String sqlCount	 = "SELECT COUNT(*) FROM transactions WHERE reference = ?";
	    
	    if (TransactionCount(sqlCount, "reference", transaction.getReference()) == 0) {
	    	
	    	 resultQuery = jtm.update(sqlInsert, 
	         		transaction.getReference(), 
	         		transaction.getAccountIban(),  
	         		new Timestamp(System.currentTimeMillis()),
	         		transaction.getAmount(),
	         		transaction.getFee(),
	         		transaction.getDescription());
	    	 
	    	 if (resultQuery==1) 
	    	 {
	    		resultQuery = 0;
	    		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
	 	    	log.info("New transaction:" );
	 	    	log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
	 	    	log.info(transaction.toString());
	 	    	log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
	    	 } 
	    	 	 		
	    } else {
	    	
	    	log.info("The reference ["+transaction.getReference() + "] already exists");
	    	
	    	resultQuery = 2;
	    }
      
        return resultQuery;
	}
	
	/*****************************************************/
	/** Get the status of the transaction in json format */
	/*****************************************************/
	
	public JsonStatusResponse getTransactionStatus(String reference, String channel) 
	{
		
		log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
    	log.info("Get Transaction Status: reference: [" + reference + "] channel: [" + channel + "]");
    	log.info("------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		JsonStatusResponse json = new JsonStatusResponse();
		
		String message = null;
		String status = null;
		String amount = null;
		String fee = null;
		
		if ( !transactionService.transactionExists(reference) )  
		{	
			//>>>>>> Business Rules[A] - The system returns the status 'INVALID' <<<<<
			message = "A transaction that is not stored in our system";
			status = "INVALID";
		} else {	
			
			List<Transaction> transactions = transactionService.findTransactionsByReference(reference);
			if (transactions != null) 
			{
				
				Date currentdate = new Date();
				Date transactiondate = new Date();
				String formatDate = "yyyy-MM-dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);
				String currentdateOnlydate = simpleDateFormat.format(currentdate);

				for (Transaction transaction : transactions) 
				{
					
					transactiondate = transaction.getDateTransaction();					
					String transactiondateOnlydate = simpleDateFormat.format(transactiondate);				
					
					message = "A transaction that is stored in our system";
					
					switch (channel) 
					{
					  case "CLIENT":
						  if (transactiondateOnlydate.equals(currentdateOnlydate)) {
							  //>>>>>> Business Rules[D] - The system returns the status 'PENDING' and the amount substracting the fee <<<<<
							  status = "PENDING";
							  amount = String.valueOf(transaction.getAmount()-transaction.getFee());
						  } else if (transactiondate.before(currentdate)) {
							  //>>>>>> Business Rules[B] - The system returns the status 'SETTLED' and the amount substracting the fee <<<<<
							  status = "SETTLED";
							  amount = String.valueOf(transaction.getAmount()-transaction.getFee());
						  } else if (transactiondate.after(currentdate)) {
							  //>>>>> Business Rules[F] - The system returns the status 'FUTURE' and the amount substracting the fee <<<<<
							  status = "FUTURE";	
							  amount = String.valueOf(transaction.getAmount()-transaction.getFee()); 
						  }
						  break;
					  case "ATM":
						  if (transactiondateOnlydate.equals(currentdateOnlydate)) {
							  //>>>>>> Business Rules[D] - The system returns the status 'PENDING' and the amount substracting the fee <<<<<
							  status = "PENDING";
							  amount = String.valueOf(transaction.getAmount()-transaction.getFee()); 
						  } else if (transactiondate.before(currentdate)) {
							  //>>>>>> Business Rules[B] - The system returns the status 'SETTLED' and the amount substracting the fee <<<<<
							  status = "SETTLED";
							  amount = String.valueOf(transaction.getAmount()-transaction.getFee());
						  } else if (transactiondate.after(currentdate)) {
							  //>>>>> Business Rules[G] - The system returns the status 'PENDING' and the amount substracting the fee <<<<<
							  status = "PENDING";	
							  amount = String.valueOf(transaction.getAmount()-transaction.getFee());
						  }
					    break;
					  case "INTERNAL":
						  if (transactiondateOnlydate.equals(currentdateOnlydate)) {
							  //>>>>>> Business Rules[E] - The system returns the status 'PENDING' <<<<<
							  status = "PENDING";
						  } else if (transactiondate.before(currentdate)) {
							  //>>>>>> Business Rules[C] - The system returns the status 'SETTLED' <<<<<
							  status = "SETTLED";	
						  } else if (transactiondate.after(currentdate)) {
							  //>>>>>> Business Rules[H] - The system returns the status 'FUTURE' <<<<<
							  status = "FUTURE";
						  }
						  //>>>>>> Business Rules[C,E,H] - The system returns the amount and the fee  <<<<<
						  amount = String.valueOf(transaction.getAmount());
						  fee = String.valueOf(transaction.getFee());
					    break;
					  default:
						message = "The type of the channel is incorrect: you can be used any of these values: CLIENT, ATM or INTERNAL";
					}		
				}					
			} else {
				message = "A transaction that is not stored in our system";
				status = "INVALID";
			}			
		}
		
		json.setMessage(message);
		json.setReference(reference);
		json.setStatus(status);
		json.setAmount(amount);
		json.setFee(fee);
		
    	log.info("*******************************************************************");
    	log.info("Response Transaction Status: ");
    	if (message != null) { log.info("message: " + json.getMessage()); }
    	if (reference != null) { log.info("reference: " + json.getReference()); }
    	if (status != null) { log.info("status: " + json.getStatus()); }
    	if (amount != null) { log.info("amount: " + json.getAmount()); }
    	if (fee != null) { log.info("fee: " + json.getFee()); }
    	log.info("*******************************************************************");
		
		return json;
	}
	
	/*********************************/
	/** Get transaction by Reference */
	/*********************************/
	@Override
	public List<Transaction> findTransactionsByReference(String reference) 
	{			
		String sqlSelect = "SELECT * FROM transactions WHERE reference = ?";

        return jtm.query(sqlSelect, new Object[] { reference }, new TransactionRowMapper());
	}	
	
	/**********************/
	/** Transaction exist */
	/**********************/
	@Override
	public boolean transactionExists(String reference) {
		
		boolean resultQuery = true;
		String sqlCount	 = "SELECT COUNT(*) FROM transactions WHERE reference = ?";
		
		if (TransactionCount(sqlCount, "reference", reference) == 0) {
			resultQuery = false;
		}
		
		return resultQuery;
	}
	
	/*********************/
	/** TransactionCount */
	/*********************/
	public int TransactionCount(String sql, String type, String field) 
	{
		
		 int resultQuery = 0;
		 
		 if (type.equals("all")) {
			 resultQuery = jtm.queryForObject(sql, Integer.class);
		 } else {
			 resultQuery = jtm.queryForObject(sql, new Object[] { field }, Integer.class); 
		 }
		 
	    return resultQuery;
	}	
	
	/***************************/
	/** Transaction Row Mapper */
	/***************************/
	private static class TransactionRowMapper implements RowMapper<Transaction> 
	{
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException 
        {
            return new Transaction(
            		rs.getLong("id"), 
            		rs.getString("reference"), 
            		rs.getString("account_iban"),
            		rs.getTimestamp("date_transaction"),
            		rs.getDouble("amount"), 
            		rs.getDouble("fee"), 
            		rs.getString("description")
            		);      	
        }
    }

}