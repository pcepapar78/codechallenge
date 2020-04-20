package com.example.bank.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties( ignoreUnknown = true )
@Getter @Setter
public class JsonStatusResponse 
{
	
	@JsonProperty("message")
	String message;

	@JsonProperty("reference")
	String reference;
	
	@JsonProperty("status")
	String status;
	
	@JsonProperty("amount")
	String amount;
	
	@JsonProperty("fee")
	String fee;
	
}
