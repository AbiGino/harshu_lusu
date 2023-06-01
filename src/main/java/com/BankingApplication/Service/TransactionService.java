package com.BankingApplication.Service;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Common.Error;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    APIResponse apiResponse;

    public ResponseEntity<APIResponse> getTransaction(Integer account_number) {

        ResponseEntity<APIResponse> apiResponseResponseEntity;
        List<Transaction> transaction = transactionRepository.findByAccountNumber(account_number);
        List<Error> errors = new ArrayList<>();
        if (transaction == null) {
// handle invalid account number
            errors.add(new Error("Invalid account number"));
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Something went wrong");
            apiResponse.setData(errors);
            apiResponseResponseEntity=new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
        } else {
            transaction = transactionRepository.findByAccountNumber(account_number);
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setMessage("Data found successfully");
            apiResponse.setData(transaction);
            apiResponseResponseEntity=new ResponseEntity<>(apiResponse,HttpStatus.OK);

        }
        return apiResponseResponseEntity;

    }

}
