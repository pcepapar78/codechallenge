package com.example.bank.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic response for the service.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse
{
    public final static int OK_CODE = 0;

    public final static String OK = "OK";

    public final static String INTERNAL_FAILURE = "Server Error";

    public final static String FORBIDDEN = "Forbidden";

    /**
     * code of the response
     */
    public int code;

    /**
     * Explanation of the response
     */
    public String explanation;
}
