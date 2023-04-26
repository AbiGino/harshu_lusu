package com.BankingApplication.Controller;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Service.WithdrawService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@Slf4j
public class WithdrawController {

    @Autowired
    WithdrawService withdrawService;
    @PutMapping("/withdraw/{account_number}")
    public ResponseEntity<APIResponse> withdraw(@Validated @PathVariable Integer account_number, @RequestParam(required = false) Integer balance) throws Exception{
        return withdrawService.withdraw(account_number,balance);
    }
}
