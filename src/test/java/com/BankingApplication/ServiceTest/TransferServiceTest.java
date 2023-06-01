package com.BankingApplication.ServiceTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Entity.Transfer;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.TransactionRepository;
import com.BankingApplication.Repository.TransferRepository;
import com.BankingApplication.Service.TransferService;
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
public class TransferServiceTest {

    @InjectMocks
    TransferService transferService;

    @Mock
    APIResponse apiResponseResponseEntity;

    @Mock
    TransferRepository transferRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp(){
        apiResponseResponseEntity.setStatus(200);
    }


    @Test
    @Order(1)
    public void transfer() {

        Account fromAccount = new Account();
        Account toAccount = new Account();
        fromAccount.setAccount_number(1);
        toAccount.setAccount_number(2);
        fromAccount.setBalance(500);
        toAccount.setBalance(100);
        when(accountRepository.findByAccountNumber(1)).thenReturn(fromAccount);
        when(accountRepository.findByAccountNumber(2)).thenReturn(toAccount);
        when(accountRepository.save(fromAccount)).thenReturn(fromAccount);
        when(accountRepository.save(toAccount)).thenReturn(toAccount);
        Transfer transfer = new Transfer();
        transfer.setFromAccountNumber(1);
        transfer.setToAccountNumber(2);
        transfer.setBalance(100);
        when(transferRepository.save(transfer)).thenReturn(transfer);
        Transaction transaction = new Transaction();
        transaction.setAccount_number(1);
        transaction.setAccount_number(2);
        transaction.setTransaction_at(12345L);
        transaction.setTransaction_type("deposit");
        transaction.setAmount(100);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        Assertions.assertEquals(HttpStatus.OK, transferService.transfer(1, 2, 100).getStatusCode());


        Transfer duplicate = new Transfer();
        duplicate.setFromAccountNumber(2);
        duplicate.setToAccountNumber(1);
        duplicate.setBalance(0);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, transferService.transfer(2, 1, 0).getStatusCode());
    }

    @Test
    @Order(2)
    public void TransferWithInvalidAccountsTest(){
        when(accountRepository.findByAccountNumber(1)).thenReturn(null);
        when(accountRepository.findByAccountNumber(2)).thenReturn(null);

        Transfer duplicate= new Transfer();
        duplicate.setBalance(0);
        duplicate.setFromAccountNumber(2);
        duplicate.setToAccountNumber(1);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,transferService.transfer(2,1,0).getStatusCode());
    }

    @Test
    @Order(3)
    public void TransferWithInsufficientBalanceFromAccount(){

        Account fromAccount = new Account();
        Account toAccount = new Account();
        when(accountRepository.findByAccountNumber(1)).thenReturn(fromAccount);
        when(accountRepository.findByAccountNumber(2)).thenReturn(toAccount);
        fromAccount.setAccount_number(1);
        toAccount.setAccount_number(2);
        fromAccount.setBalance(50);
        toAccount.setBalance(100);
        Transfer transfer = new Transfer();
        transfer.setFromAccountNumber(1);
        transfer.setToAccountNumber(2);
        transfer.setBalance(100);
        when(transferRepository.save(transfer)).thenReturn(transfer);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,transferService.transfer(1,2,100).getStatusCode());
    }
}
