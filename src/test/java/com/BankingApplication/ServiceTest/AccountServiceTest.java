package com.BankingApplication.ServiceTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Customer;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Service.AccountService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;
    @Mock
    Account account;

    @Mock
    APIResponse apiResponseResponseEntity;
    @BeforeEach
    public void Start() {
        apiResponseResponseEntity.setStatus(201);
        apiResponseResponseEntity.setData(account);
        apiResponseResponseEntity.setMessage("Customer added Successfully!");
    }

    @Test
    @Order(2)
    public void createAccount() {
        //when giving valid data
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setName("abi");
        customer.setEmail("abi@gmail.com");
        customer.setPhone_number("9363464626");
        customer.setIsActive(true);
        customer.setPassword("Abi@20");

        Account account = new Account(1, "saving", 100, true, 123456L, 987654L, customer);
        account.setAccount_number(1);
        account.setBalance(100);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, accountService.createAccount(account).getStatusCode());

//when giving invalid data
        Account duplicate = new Account();
        duplicate.setAccount_type("saving123");
        duplicate.setBalance(200);
        duplicate.setAccount_number(1);
        duplicate.setCustomer(new Customer());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, accountService.createAccount(duplicate).getStatusCode());


    }

    @Test
    @Order(1)
    public void createValidAccount() {
        //when giving valid data
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setName("abi");
        customer.setEmail("abi@gmail.com");
        customer.setPhone_number("9363464626");
        customer.setIsActive(true);
        customer.setPassword("Abi@20");

        Account account = new Account(1, "saving", 700, true, 123456L, 987654L, customer);
        when(accountRepository.save(account)).thenReturn(account);
        Assertions.assertEquals(HttpStatus.CREATED, accountService.createAccount(account).getStatusCode());
    }
}