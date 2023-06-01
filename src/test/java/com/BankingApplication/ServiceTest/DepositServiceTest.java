package com.BankingApplication.ServiceTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Deposit;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.DepositRepository;
import com.BankingApplication.Repository.TransactionRepository;
import com.BankingApplication.Service.DepositService;
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
public class DepositServiceTest {

    @InjectMocks
    DepositService depositService;
    @Mock
    Account account;
    @Mock
    Deposit deposit;
    @Mock
    AccountRepository accountRepository;

    @Mock
    DepositRepository depositRepository;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    APIResponse apiResponseResponseEntity;


//    @Test
//    public void deposit() {

    //create a mock account object
//        List<Account> account = null;
//        Customer customer = new Customer(1, "abi", "Abi@20", "9363464626", "abi@gmail.com", true, 1234567L, 9876543L, account);
//        Account account1 = new Account(1, "saving", 200, true, 2345656789L, 2345678L, customer);
//        account1.setAccount_number(1);
//        account1.setBalance(100);
//
//        //create a mock deposit object
//        Deposit deposit = new Deposit(1, 1, 200, 100);
//        deposit.setAccount_number(1);
//        deposit.setDeposit_amount(200);
//
//        //mock the repository method
//        when(accountRepository.findByAccountNumber(account1.getAccount_number())).thenReturn(account1);
//        when(accountRepository.save(account1)).thenReturn(account1);
//        when(depositRepository.save(deposit)).thenReturn(deposit);
//        when(transactionRepository.save(transaction)).thenReturn(transaction);
//
//        //when giving valid data
//        ResponseEntity<APIResponse> apiResponse=new ResponseEntity<>(apiResponseResponseEntity,HttpStatus.OK);
//        Assertions.assertEquals(HttpStatus.OK, apiResponse.getStatusCode());
//
//        //When giving invalid data
//        Account duplicate= new Account();
//        duplicate.setAccount_number(-1);
//        duplicate.setBalance(0);
//
//        apiResponseResponseEntity.setStatus(400);
//        apiResponseResponseEntity.setMessage("Deposit failed");
//        apiResponseResponseEntity.setData(null);
//
//        when(accountRepository.findByAccountNumber(account1.getAccount_number())).thenReturn(duplicate);
//        ResponseEntity<APIResponse> response=new ResponseEntity<>(apiResponseResponseEntity,HttpStatus.BAD_REQUEST);
//        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
//    }
    @BeforeEach
    public void start() {
        deposit.setAccount_number(1);
        deposit.setDeposit_amount(100);
        deposit.setBalance(500);

        apiResponseResponseEntity.setStatus(200);
    }

    @Test
    @Order(1)
    public void deposit() {
        Deposit deposit = new Deposit();
        deposit.setBalance(500);
        deposit.setAccount_number(1);
        deposit.setDeposit_amount(100);
        Transaction transaction = new Transaction();
//        account.setBalance(100);
        account.setAccount_number(1);
        when(accountRepository.findByAccountNumber(1)).thenReturn(account);
        when(depositRepository.save(deposit)).thenReturn(deposit);
        transaction.setTransaction_type("deposit");
        transaction.setAccount_number(1);
        transaction.setAmount(100);
        transaction.setTransaction_at(123456L);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        Assertions.assertEquals(HttpStatus.OK,depositService.deposit(1,100).getStatusCode());

        Deposit duplicate = new Deposit();
        duplicate.setDeposit_amount(0);
        duplicate.setAccount_number(0);
        duplicate.setBalance(0);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,depositService.deposit(0, 500).getStatusCode());
    }

    @Test
    @Order(2)
    public void DepositInvalidBalanceTest(){

        Account account1=new Account();
        account1.setAccount_number(1);
        account1.setBalance(0);
        when(accountRepository.findByAccountNumber(1)).thenReturn(account1);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,depositService.deposit(1,0).getStatusCode());
    }

}
