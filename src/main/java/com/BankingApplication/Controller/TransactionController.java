package com.BankingApplication.Controller;

import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/getTransaction/{account_number}")
    public List<Transaction> getTransactionDetails(@Validated @PathVariable Integer account_number) throws Exception{
        return transactionService.getTransaction(account_number);
    }
}
