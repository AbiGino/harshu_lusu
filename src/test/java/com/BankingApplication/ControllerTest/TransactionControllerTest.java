package com.BankingApplication.ControllerTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Controller.TransactionController;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Service.TransactionService;
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

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransactionControllerTest {

    @InjectMocks
    TransactionController transactionController;

    @Mock
    TransactionService transactionService;

    @Mock
    APIResponse apiResponseResponseEntity;

    Transaction transaction;

    @BeforeEach
    public void start() {
        transaction=new Transaction(1,122,2,21L,"deposit");
    }

    @Test
    public void getTransaction() throws Exception {
        apiResponseResponseEntity.setStatus(200);
        apiResponseResponseEntity.setMessage("Data found");
        apiResponseResponseEntity.setData(transaction);
        apiResponseResponseEntity.setError(null);
        ResponseEntity<APIResponse> response = new ResponseEntity<>(apiResponseResponseEntity,HttpStatus.OK);
        when(transactionService.getTransaction(1)).thenReturn(response);
        Assertions.assertEquals(transactionController.getTransaction(1).getStatusCode(),HttpStatus.OK);
    }

}
