package com.BankingApplication.ControllerTest;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Controller.LoginController;
import com.BankingApplication.DTO.LoginRequestDTO;
import com.BankingApplication.Service.LoginService;
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
public class LoginControllerTest {
    @InjectMocks
    LoginController loginController;

    @Mock
    LoginService loginService;

    @Mock
    APIResponse apiResponse;

    @Mock
    LoginRequestDTO loginRequestDTO;

    @BeforeEach
    public void Start(){
        LoginRequestDTO loginRequestDTO=new LoginRequestDTO("abi","Abi@20");
    }
    @Test
    @Order(1)
    public void login(){
        loginRequestDTO.setName("abi");
        loginRequestDTO.setPassword("Abi@20");

        apiResponse.setStatus(200);
        apiResponse.setData(loginRequestDTO);
        apiResponse.setMessage("Logged in Successfully");
        ResponseEntity<APIResponse> response=new ResponseEntity<>(apiResponse, HttpStatus.OK);
        when(loginService.login(loginRequestDTO)).thenReturn(response);
        ResponseEntity<APIResponse> result=loginController.login(loginRequestDTO);
        Assertions.assertEquals(HttpStatus.OK,result.getStatusCode());
    }
}
