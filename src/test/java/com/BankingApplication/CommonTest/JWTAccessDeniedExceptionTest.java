package com.BankingApplication.CommonTest;

import com.BankingApplication.Common.JWTAccessDeniedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JWTAccessDeniedExceptionTest {

    @Test
    void testConstructor() {
        String message = "Access denied for JWT token.";
        JWTAccessDeniedException exception = new JWTAccessDeniedException(message);
        assertEquals(message, exception.getMessage());
    }
}
