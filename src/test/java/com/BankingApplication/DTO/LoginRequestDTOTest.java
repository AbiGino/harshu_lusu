package com.BankingApplication.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginRequestDTOTest {

    @Test
    public void testGettersAndSetters() {
        LoginRequestDTO loginRequest = new LoginRequestDTO();
        loginRequest.setName("abi");
        loginRequest.setPassword("Abi@20");

        assertEquals("abi", loginRequest.getName());
        assertEquals("Abi@20", loginRequest.getPassword());
    }
}
