package com.example.bank.response;

import java.util.concurrent.ThreadLocalRandom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse
{
    public final static String OK = "OK";
    public final static String INTERNAL_FAILURE = "Server Error";
    public final static String FORBIDDEN = "Forbidden";
    
    public static final TransactionResponse RESPONSE_SAVE_TRANSACTION = new TransactionResponse( "0" );
    
    public static final TransactionResponse RESPONSE_ERROR_TRANSACTION = new TransactionResponse( "1" );
    
    /** 2: solicitud rechazada, la referencia ya existe. */
    public static final TransactionResponse RESPONSE_REFERENCE_EXISTS = new TransactionResponse( "2" );
    
    /** 3 (INVALID): solicitud rechazada, la referencia no exite. */
    public static final TransactionResponse RESPONSE_REFERENCE_NO_EXISTS = new TransactionResponse( "3" );

    private String responseCode;

    public static TransactionResponse getRandom()
    {
        int random = ThreadLocalRandom.current().nextInt( 0, 3 );
        switch ( random )
        {
            case 0:
                return RESPONSE_SAVE_TRANSACTION;
            case 1:
                return RESPONSE_ERROR_TRANSACTION;
            case 2:
                return RESPONSE_REFERENCE_EXISTS;
            case 3:
                return RESPONSE_REFERENCE_NO_EXISTS;
            default: return null;
        }
    }
}
