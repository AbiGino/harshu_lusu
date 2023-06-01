package com.BankingApplication.CommonTest;

import com.BankingApplication.Common.InsufficientBalanceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsufficientBalanceExceptionTest {

    @Test
    public void testConstructorAndGetMessage() {
        String message = "Insufficient balance in account";
        InsufficientBalanceException exception = new InsufficientBalanceException(message);
        assertEquals(message, exception.getMessage());
    }
}
