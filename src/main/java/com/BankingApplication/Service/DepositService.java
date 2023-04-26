package com.BankingApplication.Service;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Common.AccountValidation;
import com.BankingApplication.Common.Error;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Deposit;
import com.BankingApplication.Entity.Transaction;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.DepositRepository;
import com.BankingApplication.Repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@Service
@Slf4j
public class DepositService {


    @Autowired
    DepositRepository depositRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    APIResponse apiResponse;

    //To deposit amount
    public ResponseEntity<APIResponse> deposit(Integer account_number, Integer balance) {
        ResponseEntity<APIResponse> apiResponseResponseEntity;
        Account account = accountRepository.findByAccountNumber(account_number);
        LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();
        Deposit deposit = new Deposit();
        Transaction transaction = new Transaction();

        if (account != null && balance != null && balance > 0) {
            List<Error> errors;
            errors = AccountValidation.validateAccount(account);
            Integer amount = balance + account.getBalance();
            account.setBalance(amount);
            accountRepository.save(account);

            deposit.setBalance(amount);
            deposit.setAccount_number(account_number);
            deposit.setDeposit_amount(balance);
            depositRepository.save(deposit);

            responseData.put("Deposit", deposit);
            transaction.setAccount_number(account_number);
            transaction.setTransaction_at(LocalDateTime.now());
            transaction.setAmount(balance);
            transaction.setTransaction_type("deposit");
            transactionRepository.save(transaction);

            responseData.put("transaction", transaction);
            apiResponse.setStatus(200);
            apiResponse.setData(responseData);
            apiResponse.setMessage("Amount Deposited Successfully!");
            log.info("Amount deposited Successfully!");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            List<Error> errors = new ArrayList<>();
            if (account == null) {
                errors.add(new Error("Invalid Account Number"));
            }
            if (balance == null || balance <= 0) {
                errors.add(new Error("Invalid balance amount"));
            }

            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setData(errors);
            apiResponse.setError("Amount Deposit Failed!");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

        }
        return apiResponseResponseEntity;
    }
}
//    public ResponseEntity<APIResponse> deposit(Integer account_number, Integer balance) {
//        ResponseEntity<APIResponse> apiResponseResponseEntity ;
//        Customer customer = customerRepository.findByAccountNumber(account_number);
//        LinkedHashMap<String, Object> responseData=new LinkedHashMap<>();
//        Deposit deposit = new Deposit();
//        Transaction transaction=new Transaction();
//       // LocalDateTime localTime = LocalDateTime.now();
//        if (customer != null)
//        {
//            Integer bal = balance + customer.getBalance();
//            customer.setBalance(bal);
//            customerRepository.save(customer);
//
//            deposit.setBalance(bal);
//            deposit.setAccount_number(account_number);
//            deposit.setDeposit_amount(balance);
//            depositRepository.save(deposit);
//
//            responseData.put("Deposit",deposit);
//            transaction.setAccount_number(account_number);
//            transaction.setTransaction_at(LocalDateTime.now());
//            transaction.setAmount(balance);
//            transactionRepository.save(transaction);
//
//            responseData.put("transaction",transaction);
//            apiResponse.setStatus(200);
//            apiResponse.setData(responseData);
//            apiResponse.setMessage("Amount Deposited Successfully!");
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
//
//        } else {
//            if (customer == null) {
//                apiResponse.setMessage("Invalid Account number Entered");
//                apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//
//            } else {
//                apiResponse.setStatus(400);
//                apiResponse.setData(new ArrayList<>());
//                apiResponse.setError("Deposit failed!");
//
//            }
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//
//        }
//        return apiResponseResponseEntity;
//    }

//    public ResponseEntity<APIResponse> deposit(Integer account_number, Integer balance) {
//        ResponseEntity<APIResponse> apiResponseResponseEntity;
//        Account account = accountRepository.findByAccountNumber(account_number);
//        LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();
//        Deposit deposit = new Deposit();
//        Transaction transaction = new Transaction();
//
//            if (account != null && balance != null && balance > 0) {
//               List<Error> errors;
//               errors = AccountValidation.validateAccount(account);
//                Integer amount = balance + account.getBalance();
//                account.setBalance(amount);
//                accountRepository.save(account);
//
//                deposit.setBalance(amount);
//                deposit.setAccount_number(account_number);
//                deposit.setDeposit_amount(balance);
//                depositRepository.save(deposit);
//
//                responseData.put("Deposit", deposit);
//                transaction.setAccount_number(account_number);
//                transaction.setTransaction_at(LocalDateTime.now());
//                transaction.setAmount(balance);
//                transactionRepository.save(transaction);
//
//                responseData.put("transaction", transaction);
//                apiResponse.setStatus(200);
//                apiResponse.setData(responseData);
//                apiResponse.setMessage("Amount Deposited Successfully!");
//                log.info("Amount deposited Successfully!");
//                apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
//            } else {
//                    List<Error> errors = new ArrayList<>();
//                    if (account == null) {
//                        errors.add(new Error("Invalid Account Number"));
//                    }
//                    if (balance == null || balance <= 0) {
//                        errors.add(new Error("Invalid balance amount"));
//                    }
//
//                    apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//                    apiResponse.setData(errors);
//                    apiResponse.setError("Amount Deposit Failed!");
//                    apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//
//                }
//                return apiResponseResponseEntity;
//            }
//    }


//    public ResponseEntity<APIResponse> deposit(Integer account_number, Integer amount, String transaction_type) {
//        ResponseEntity<APIResponse> apiResponseResponseEntity;
//        Customer customer = customerRepository.findByAccountNumber(account_number);
//        LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();
//        if (customer != null) {
//            int balance = customer.getBalance();
//            if ("deposit".equals(transaction_type)) {
//                balance += amount;
//            } else if ("withdrawal".equals(transaction_type)) {
//                balance -= amount;
//            }
//            customer.setBalance(balance);
//            customerRepository.save(customer);
//
//            Transaction transaction = new Transaction();
//            transaction.setAccount_number(account_number);
//            transaction.setTransaction_at(LocalDateTime.now());
//            transaction.setAmount(amount);
//            transaction.setTransaction_type(transaction_type);
//            transactionRepository.save(transaction);
//
//            responseData.put("transaction", transaction);
//            apiResponse.setStatus(200);
//            apiResponse.setData(responseData);
//            apiResponse.setMessage("Transaction Completed Successfully!");
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
//        } else {
//            apiResponse.setMessage("Invalid Account number Entered");
//            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//        }
//        return apiResponseResponseEntity;
//    }

