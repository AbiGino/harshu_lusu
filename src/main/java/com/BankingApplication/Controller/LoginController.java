package com.BankingApplication.Controller;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.DTO.LoginRequestDTO;
import com.BankingApplication.Service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@Slf4j
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public APIResponse login(@RequestBody LoginRequestDTO loginRequestDTO){
        return loginService.login(loginRequestDTO);
    }
}
