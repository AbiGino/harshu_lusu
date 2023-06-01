package com.BankingApplication.ServiceTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Repository.TransactionRepository;
import com.BankingApplication.Service.TransactionService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    APIResponse apiResponseResponseEntity;
    @Mock
    Transaction transaction;
    @Mock
    TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp(){
        transaction.setAccount_number(1);
        transaction.setAmount(100);
        transaction.setTransaction_type("withdraw");

        apiResponseResponseEntity.setStatus(200);
        apiResponseResponseEntity.setData(transaction);
    }

    @Test
    @Order(1)
    public void transactionServiceTest(){

       Transaction transaction=new Transaction();
       transaction.setAccount_number(1);
       Transaction transaction1=new Transaction();
       transaction1.setAccount_number(2);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        transactionList.add(transaction1);
        when(transactionRepository.findByAccountNumber(1)).thenReturn(transactionList);
        Assertions.assertEquals(HttpStatus.OK,transactionService.getTransaction(1).getStatusCode());

        List<Transaction> duplicate1 = null;
        when(transactionRepository.findByAccountNumber(0)).thenReturn(duplicate1);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, transactionService.getTransaction(0).getStatusCode());
    }


}
