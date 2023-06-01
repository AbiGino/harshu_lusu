package com.BankingApplication.ControllerTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Controller.AccountController;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Customer;
import com.BankingApplication.Service.AccountService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountService accountService;

    @Mock
    APIResponse apiResponse;


    @Mock
    Account account;

    @BeforeEach
    public void Start(){
        Customer customer=new Customer();
        Account account=new Account(1,"saving",500,true,System.currentTimeMillis(),System.currentTimeMillis(),customer);
    }

    @Test
    @Order(1)
    public void createAccount(){
        account.setAccount_number(1);
        account.setAccount_type("saving");
        account.setBalance(500);

        apiResponse.setStatus(201);
        apiResponse.setMessage("Account created Successfully");
        apiResponse.setData(account);
        ResponseEntity<APIResponse> response=new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        when(accountService.createAccount(account)).thenReturn(response);
        ResponseEntity<APIResponse> result=accountController.createAccount(account);
        Assertions.assertEquals(HttpStatus.CREATED,result.getStatusCode());
    }

}
