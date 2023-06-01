package com.BankingApplication.Service;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Common.Error;
import com.BankingApplication.Common.InvalidAccountException;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Entity.Withdraw;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.TransactionRepository;
import com.BankingApplication.Repository.WithdrawRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Slf4j
public class WithdrawService {

    @Autowired
    WithdrawRepository withdrawRepository;

    @Autowired
    APIResponse apiResponse;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    //To withdraw Amount
    public ResponseEntity<APIResponse> withdraw(Integer account_number, Integer balance) throws InvalidAccountException {
        ResponseEntity<APIResponse> apiResponseResponseEntity;
        Account account = accountRepository.findByAccountNumber(account_number);
        LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();
        Withdraw withdraw = new Withdraw();
        Transaction transaction = new Transaction();
        Long timeStamp = (Long) Instant.now().getEpochSecond();
        if (account != null && balance != null && balance > 0) {
            Integer amount = account.getBalance() - balance;
            account.setBalance(amount);
            System.out.println(amount);
            accountRepository.save(account);

            withdraw.setAccount_number(account_number);
            withdraw.setBalance(amount);
            System.out.println(balance);
            withdraw.setWithdraw_amount(balance);
            withdrawRepository.save(withdraw);

            responseData.put("Withdraw", withdraw);
            transaction.setAccount_number(account_number);
            transaction.setTransaction_at(timeStamp);
            transaction.setAmount(balance);
            transaction.setTransaction_type("withdraw");
            transactionRepository.save(transaction);
            responseData.put("Transaction", transaction);

            apiResponse.setStatus(200);
            apiResponse.setData(responseData);
            apiResponse.setMessage("Amount Withdrawn Successfully!");
            log.info("Amount Withdrawn Successfully");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            List<Error> errors = new ArrayList<>();
            if (account == null) {
                errors.add(new Error("Invalid Account Number"));
            }
            if (balance == null || balance <= 0) {
                errors.add(new Error("Invalid balance amount"));
            } else if (account != null && account.getBalance() < balance) {
                errors.add(new Error("Insufficient balance"));
            }
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setData(errors);
            apiResponse.setError("Amount Withdrawn Failed!");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

        }
        return apiResponseResponseEntity;
    }
}
