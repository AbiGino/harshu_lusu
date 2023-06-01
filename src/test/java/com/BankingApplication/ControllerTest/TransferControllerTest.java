package com.BankingApplication.ControllerTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Controller.TransferController;
import com.BankingApplication.Service.TransferService;
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
public class TransferControllerTest {

    @InjectMocks
    TransferController transferController;
    @Mock
    TransferService transferService;

    @Mock
    APIResponse apiResponseResponseEntity;

    @BeforeEach
    public void start() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Order(1)
    public void transfer() throws Exception {

        Integer fromAccountNumber = 1;
        Integer toAccountNumber = 2;
        Integer balance = 100;
        ResponseEntity<APIResponse> apiResponse = new ResponseEntity<>(apiResponseResponseEntity, HttpStatus.OK);
        when(transferService.transfer(fromAccountNumber,toAccountNumber, balance)).thenReturn(apiResponse);
        ResponseEntity<APIResponse> result = transferController.transfer(fromAccountNumber,toAccountNumber, balance);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
