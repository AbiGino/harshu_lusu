package com.BankingApplication.Service;

import com.BankingApplication.Common.Error;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> getTransaction(Integer account_number) {


        List<Transaction> transaction = transactionRepository.findByAccountNumber(account_number);
//            List<Transaction> transaction = null;
        List<Error> errors = new ArrayList<>();
        if (transaction == null) {
// handle invalid account number
            errors.add(new Error("Invalid account number"));
        } else {
            transaction = transactionRepository.findByAccountNumber(account_number);

        }
        return transaction;

    }

}
