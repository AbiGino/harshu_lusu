package com.BankingApplication.CommonTest;

import com.BankingApplication.Common.InvalidDataFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvalidDataFoundExceptionTest {
    @Test
    public void testException() {
        String message = "Invalid data found";
        InvalidDataFoundException exception = assertThrows(InvalidDataFoundException.class,
                () -> {
                    throw new InvalidDataFoundException(message);
                });
        assertEquals(message, exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
}
