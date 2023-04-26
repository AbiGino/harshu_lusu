package com.BankingApplication.Controller;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Service.DepositService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@Slf4j
public class DepositController {

    @Autowired
    DepositService depositService;

    @PutMapping("/deposit/{account_number}")
    public ResponseEntity<APIResponse> deposit(@PathVariable Integer account_number, @RequestParam Integer balance) throws Exception{
        return depositService.deposit(account_number,balance);
    }
}
