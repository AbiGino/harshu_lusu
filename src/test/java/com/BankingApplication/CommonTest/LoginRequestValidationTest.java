package com.BankingApplication.CommonTest;

import com.BankingApplication.Common.Error;
import com.BankingApplication.Common.LoginRequestValidation;
import com.BankingApplication.DTO.LoginRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LoginRequestValidationTest {

    @Test
    public void testValidateLogin() {
        // Initialize test data
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setName("");
        loginRequestDTO.setPassword(null);

        // Call the method under test
        List<Error> errors = LoginRequestValidation.validateLogin(loginRequestDTO);

        // Assert that the expected errors are returned
        Assertions.assertEquals(2, errors.size());
        Assertions.assertEquals("Name cannot be empty", errors.get(0).getError());
        Assertions.assertEquals("Password cannot be empty", errors.get(1).getError());
    }

}
