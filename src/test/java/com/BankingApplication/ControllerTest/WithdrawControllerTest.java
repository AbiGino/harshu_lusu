package com.BankingApplication.ControllerTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Controller.WithdrawController;
import com.BankingApplication.Service.WithdrawService;
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
public class WithdrawControllerTest {

    @InjectMocks
    WithdrawController withdrawController;

    @Mock
    WithdrawService withdrawService;

    @Mock
    APIResponse apiResponseResponseEntity;

    @BeforeEach
    public void start() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @Order(1)
    public void withdraw() throws Exception {

        Integer account_number = 1;
        Integer balance = 100;
        ResponseEntity<APIResponse> apiResponse = new ResponseEntity<>(apiResponseResponseEntity, HttpStatus.OK);
        when(withdrawService.withdraw(account_number, balance)).thenReturn(apiResponse);
        ResponseEntity<APIResponse> result = withdrawController.withdraw(account_number, balance);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
