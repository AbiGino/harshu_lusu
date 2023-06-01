package com.BankingApplication.CommonTest;

import com.BankingApplication.Common.AccountValidation;
import com.BankingApplication.Common.Error;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountValidationTest {

    @Test
    public void testValidateAccount() {

        Account account = new Account();
        Customer customer = new Customer();

        account.setAccount_type("Savings");
        account.setBalance(1000);
        customer.setName("abi");
        customer.setEmail("abi@gmail.com");
        customer.setPhone_number("9363464626");
        customer.setPassword("Abi@20");

        account.setCustomer(customer);

        List<Error> errors = AccountValidation.validateAccount(account);

        assertEquals(0, errors.size(), "There should be no errors");

    }
}
