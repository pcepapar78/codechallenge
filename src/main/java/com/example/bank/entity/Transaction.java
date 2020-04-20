package com.example.bank.entity;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	  
	@Size(min = 6, max = 6)
	@Column(name="REFERENCE")
	private String reference;
	  
	@Size(min = 24, max = 24)
	@Column(name="ACCOUNT_IBAN")
	private String accountIban;
	   
	@Column(name="DATE_TRANSACTION")
	private Timestamp date_transaction;
	  
	@Column(name="AMOUNT")
	private double amount;
	  
	@Column(name="FEE")
	private double fee;
	 
	@Size(min = 0, max = 100)
	@Column(name="description")
	private String description;
   
    public Transaction() {}
    
    public Transaction( Long id, String reference, String accountIban, Timestamp date_transaction, double amount, double fee, String description) 
    {
    	this.id = id;
        this.reference = reference;
        this.accountIban = accountIban;
        this.date_transaction = date_transaction;
    	this.amount = amount;
    	this.fee = fee;
    	this.description = description;
    }    
    
    public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}	

	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public String getAccountIban() {
		return accountIban;
	}
	
	public void setAccountIban(String accountIban) {
		this.accountIban = accountIban;
	}
	
	public Timestamp getDateTransaction() {
		return date_transaction;
	}
	
	public void setDateTransaction(Timestamp date_transaction) {
		this.date_transaction = date_transaction;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFee() {
		return fee;
	}
	
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, reference, accountIban, date_transaction, amount, fee, description );
    }
	
	@Override
    public String toString() 
	{
		
		Timestamp dateaux = null;
		
		if (date_transaction == null) {
			dateaux = new Timestamp(System.currentTimeMillis());
		} else {
			dateaux = date_transaction;
		}
		
        return new StringJoiner(", ", Transaction.class.getSimpleName() + "[", "]")
                .add("reference='" + reference + "'")
                .add("accountIban='" + accountIban + "'")
                .add("date_transaction=" + dateaux + "")
                .add("amount=" + amount + "")
                .add("fee=" + fee + "")
                .add("description='" + description + "'")
                .toString();
    }
}