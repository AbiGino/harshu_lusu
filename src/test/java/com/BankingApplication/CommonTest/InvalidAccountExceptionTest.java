package com.BankingApplication.CommonTest;

import com.BankingApplication.Common.InvalidAccountException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class InvalidAccountExceptionTest {

    @Test
    public void testInvalidAccountException() {
        String message = "Invalid account number";
        try {
            throw new InvalidAccountException(message);
        } catch (InvalidAccountException e) {
            assertEquals(message, e.getMessage());
        }
    }
}
