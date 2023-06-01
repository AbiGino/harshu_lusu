package com.BankingApplication.ServiceTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Customer;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.CustomerRepository;
import com.BankingApplication.Service.CustomerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    Customer customer;

    @Mock
    APIResponse apiResponseResponseEntity;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    AccountRepository accountRepository;

    @BeforeEach
    public void start() {
        customer.setName("abi");
        customer.setEmail("abi@gmail.com");
        customer.setPhone_number("9363464626");
        customer.setCustomer_id(1);
        customer.setPassword("Abi@20");
    }

    @Test
    @Order(1)
    public void createCustomer() {
//when giving valid data
        apiResponseResponseEntity.setStatus(201);
        apiResponseResponseEntity.setData(customer);
        apiResponseResponseEntity.setMessage("Customer added Successfully!");
        Account account = new Account();
        account.setAccount_number(1);
        account.setBalance(100);

        Account account2 = new Account();
        account2.setAccount_number(1);
        account2.setBalance(700);
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        accountList.add(account2);
        Customer customer = new Customer(1, "abi", "Abi@20", "9363464626", "abi@gmail.com", true, 123456789L, 14387545L, accountList);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(bCryptPasswordEncoder.encode("Abi@20")).thenReturn("hqWsOuTYekKsnQsk");
        Assertions.assertEquals(HttpStatus.CREATED, customerService.createCustomer(customer).getStatusCode());

//when giving invalid data
        Customer duplicate = new Customer();
        duplicate.setName("abi1234");
        duplicate.setEmail("abi");
        duplicate.setPhone_number("987654356fd");
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, customerService.createCustomer(duplicate).getStatusCode());
    }

    @Test
    @Order(2)
    public void getAccountsByCustomerId() {
        //when giving valid data
        Account account = new Account();
        account.setAccount_number(1);
        account.setBalance(100);

        Account account2 = new Account();
        account2.setAccount_number(1);
        account2.setBalance(700);
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        accountList.add(account2);
        customer.setCustomer_id(1);
        apiResponseResponseEntity.setMessage("Data found");
        apiResponseResponseEntity.setStatus(200);
        apiResponseResponseEntity.setError(null);
        apiResponseResponseEntity.setData(customer);
        when(accountRepository.findAccountsByCustomerId(1)).thenReturn(accountList);
        Assertions.assertEquals(HttpStatus.OK, customerService.getAccountsByCustomerId(1).getStatusCode());

        //when giving invalid data
        List<Account> accountList1 = null;
        when(accountRepository.findAccountsByCustomerId(2)).thenReturn(accountList1);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, customerService.getAccountsByCustomerId(2).getStatusCode());

    }

    @Test
    @Order(3)
    public void getAccount() {
        //when giving valid data
        Account account = new Account();
        account.setAccount_number(1);
        when(accountRepository.findByAccountNumber(1)).thenReturn(account);
        Assertions.assertEquals(HttpStatus.OK, customerService.getAccount(1).getStatusCode());

        //when giving invalid data
        Account duplicate = null;
        when(accountRepository.findByAccountNumber(2)).thenReturn(duplicate);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, customerService.getAccount(2).getStatusCode());
    }
}
