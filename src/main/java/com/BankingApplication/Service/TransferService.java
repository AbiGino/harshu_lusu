package com.BankingApplication.Service;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Common.Error;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Entity.Transfer;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.TransactionRepository;
import com.BankingApplication.Repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TransferService {


    @Autowired
    APIResponse apiResponse;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public ResponseEntity<APIResponse> transfer(Integer fromAccountNumber, Integer toAccountNumber, Integer balance) {
        ResponseEntity<APIResponse> apiResponseResponseEntity;
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
        Transfer transfer = new Transfer();
        LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();
        Transaction transaction = new Transaction();

        if (fromAccount != null && toAccount != null && balance != null && balance > 0 && fromAccount.getBalance() >= balance) {
            fromAccount.setBalance(fromAccount.getBalance() - balance);
            toAccount.setBalance(toAccount.getBalance() + balance);
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            transfer.setBalance(balance);
            transfer.setFromAccountNumber(fromAccountNumber);
            transfer.setToAccountNumber(toAccountNumber);
            transferRepository.save(transfer);

            responseData.put("Transfer", transfer);
            transaction.setAccount_number(fromAccountNumber);
            transaction.setTransaction_at(LocalDateTime.now());
            transaction.setAmount(balance);
            transactionRepository.save(transaction);

            transaction.setAccount_number(toAccountNumber);
            transaction.setTransaction_at(LocalDateTime.now());
            transaction.setAmount(balance);
            transactionRepository.save(transaction);

            apiResponse.setStatus(200);
            apiResponse.setMessage("Amount transferred Successfully!");
            apiResponse.setData("FROM ACCOUNT -> " + fromAccount.toString() + " TO ACCOUNT -> " + toAccount.toString());
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            List<Error> errors = new ArrayList<>();
            if (fromAccount == null) {
                errors.add(new Error("Invalid fromAccountNumber"));
            }
            if (toAccount == null) {
                errors.add(new Error("Invalid toAccountNumber"));
            }
            if (balance == null || balance <= 0) {
                errors.add(new Error("Invalid balance amount"));
            }
            if (fromAccount != null && fromAccount.getBalance() < balance) {
                errors.add(new Error("Insufficient balance in fromAccount"));
            }

            apiResponse.setStatus(400);
            apiResponse.setData(errors);
            apiResponse.setError("Amount Transfer Failed!");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        return apiResponseResponseEntity;
    }

}
