package com.BankingApplication.Controller;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@Slf4j
public class TransferController {
    @Autowired
    TransferService transferService;

    @PostMapping("/{fromAccountNumber}/transfer/{toAccountNumber}")
    public ResponseEntity<APIResponse> transfer(@Validated @PathVariable Integer fromAccountNumber, @PathVariable Integer toAccountNumber, @RequestParam Integer balance) {
        log.info("Amount Transferred Successfully");
        return transferService.transfer(fromAccountNumber, toAccountNumber, balance);
    }
}
