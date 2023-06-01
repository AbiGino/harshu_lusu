package com.BankingApplication.EntityTest;

import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    private List<Account> account;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomer_id(1);
        customer.setName("abi");
        customer.setPassword("Abi@20");
        customer.setPhone_number("9363464626");
        customer.setEmail("abi@gmail.com");
        customer.setIsActive(true);
        customer.setCreatedAt(1683695210557L);
        customer.setUpdatedAt(1683695210557L);

        account = new ArrayList<>();
        Account account1 = new Account();
        account1.setAccount_number(1001);
        account1.setAccount_type("Savings");
        account1.setBalance(1000);
        account1.setIsActive(true);
        account1.setCreatedAt(123456L);
        account1.setUpdatedAt(98765432L);

        Account account2 = new Account();
        account2.setAccount_number(1002);
        account2.setAccount_type("Checking");
        account2.setBalance(500);
        account2.setIsActive(false);
        account2.setCreatedAt(1683695210557L);
        account2.setUpdatedAt(1683695210557L);

        account.add(account1);
        account.add(account2);
        customer.setAccount(account);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1, customer.getCustomer_id());
        assertEquals("abi", customer.getName());
        assertEquals("Abi@20", customer.getPassword());
        assertEquals("9363464626", customer.getPhone_number());
        assertEquals("abi@gmail.com", customer.getEmail());
        assertEquals(true, customer.getIsActive());
        assertNotNull(customer.getCreatedAt());
        assertNotNull(customer.getUpdatedAt());
        assertEquals(account, customer.getAccount());
    }

//    @Test
//    void testToString() {
//        assertEquals("Customer(customer_id=1, name=abi, password=Abi@20, phone_number=9363464626, email=abi@gmail.com, isActive=true, createdAt=" + customer.getCreatedAt() + ", updatedAt=" + customer.getUpdatedAt() + ", account=" + account + ")", customer.toString());
//    }
}
