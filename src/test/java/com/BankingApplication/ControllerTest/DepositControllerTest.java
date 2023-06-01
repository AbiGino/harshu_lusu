package com.BankingApplication.ControllerTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Controller.DepositController;
import com.BankingApplication.Service.DepositService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepositControllerTest {


    @InjectMocks
    DepositController depositController;


    @Mock
    DepositService depositService;

    @Mock
    APIResponse apiResponseResponseEntity;

    @BeforeEach
    public void start() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @Order(1)
    public void deposit() throws Exception {

        Integer account_number = 1;
        Integer balance = 100;
        ResponseEntity<APIResponse> apiResponse = new ResponseEntity<>(apiResponseResponseEntity, HttpStatus.OK);
        when(depositService.deposit(account_number, balance)).thenReturn(apiResponse);
        ResponseEntity<APIResponse> result = depositController.deposit(account_number, balance);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
