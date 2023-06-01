package com.BankingApplication.ControllerTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Controller.CustomerController;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Customer;
import com.BankingApplication.Service.AccountService;
import com.BankingApplication.Service.CustomerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
//provide additional capabilities to the test class
@ExtendWith(MockitoExtension.class)
//To configure the mockito framework in unit test
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    @Mock
    APIResponse apiResponse;

    @Mock
    Customer customer;
    @Mock
    AccountService accountService;

    @BeforeEach
    public void start(){
        List<Account> account= new ArrayList<>();
        Customer customer = new Customer(1,"abi","Abi@20","9363464626","abi@gmail.com",true,System.currentTimeMillis(),System.currentTimeMillis(),account);
    }
    @Test
    @Order(1)
    public void createCustomer(){

        customer.setName("abi");
        customer.setEmail("abi@gmail.com");
        customer.setPhone_number("9363464626");

        apiResponse.setStatus(201);
        apiResponse.setData(customer);
        apiResponse.setMessage("Successfully created a new customer");
        ResponseEntity<APIResponse> response=new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
        when(customerService.createCustomer(customer)).thenReturn(response);
        ResponseEntity<APIResponse> result = customerController.createCustomer(customer);
        Assertions.assertEquals(HttpStatus.CREATED,result.getStatusCode());
    }

    @Test
    @Order(2)
    public void getAccountsByCustomerId(){
        customer.setCustomer_id(1);
        ResponseEntity<APIResponse> response=new ResponseEntity<>(apiResponse,HttpStatus.OK);
        when(customerService.getAccountsByCustomerId(customer.getCustomer_id())).thenReturn(response);
        ResponseEntity<APIResponse> result=customerController.getAccountsByCustomerId(customer.getCustomer_id());
        Assertions.assertEquals(HttpStatus.OK,result.getStatusCode());
    }


    @Test
    @Order(3)
    public void getAccount() throws Exception {
        Account account=new Account();
        account.setAccount_number(1);
        ResponseEntity<APIResponse> response = new ResponseEntity<>(apiResponse,HttpStatus.OK);
        when(customerService.getAccount(1)).thenReturn(response);
        ResponseEntity<APIResponse> responseEntity=customerController.getAccount(1);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
}
