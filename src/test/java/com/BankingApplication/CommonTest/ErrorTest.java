package com.BankingApplication.CommonTest;

import com.BankingApplication.Common.Error;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorTest {

    @Test
    void testToString() {
        Error error = new Error("Something went wrong");
        assertEquals("Something went wrong", error.toString());
    }
}
