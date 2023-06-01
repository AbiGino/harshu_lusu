package com.BankingApplication.EntityTest;

import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    private Account account;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        // Create a new customer for each test case
        customer = new Customer();
        customer.setName("abi");
        customer.setEmail("abiberly@gmail.com");

        // Create a new account for each test case
        account = new Account();
        account.setAccount_number(12345);
        account.setAccount_type("Savings");
        account.setBalance(1000);
        account.setIsActive(true);
        account.setCreatedAt(123456L);
        account.setUpdatedAt(987654L);
        account.setCustomer(customer);
    }

    @Test
    public void testGetAccountNumber() {
        assertEquals(12345, account.getAccount_number());
    }

    @Test
    public void testGetAccountType() {
        assertEquals("Savings", account.getAccount_type());
    }

    @Test
    public void testGetBalance() {
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void testIsActive() {
        Assertions.assertTrue(account.getIsActive());
    }

    @Test
    public void testGetCreatedAt() {
        Assertions.assertNotNull(account.getCreatedAt());
    }

    @Test
    public void testGetUpdatedAt() {
        Assertions.assertNotNull(account.getUpdatedAt());
    }

    @Test
    public void testGetCustomer() {
        assertEquals(customer, account.getCustomer());
    }


}
