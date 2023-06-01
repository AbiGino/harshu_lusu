package com.BankingApplication.CommonTest;

import com.BankingApplication.Common.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomExceptionTest {
    @Test
    public void testException() {
        String message = "Custom exception message";
        CustomException exception = assertThrows(CustomException.class, () -> {
            throw new CustomException(message);
        });

        assertEquals(message, exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }
}
