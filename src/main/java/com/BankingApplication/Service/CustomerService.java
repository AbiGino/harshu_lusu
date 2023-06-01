package com.BankingApplication.Service;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Common.CustomerValidation;
import com.BankingApplication.Common.Error;
import com.BankingApplication.Entity.Account;
import com.BankingApplication.Entity.Customer;
import com.BankingApplication.Repository.AccountRepository;
import com.BankingApplication.Repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    APIResponse apiResponse;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    //To create account

    public ResponseEntity<APIResponse> createCustomer(Customer customer) {
        ResponseEntity<APIResponse> apiResponseResponseEntity=null;
        List<Account> account = customer.getAccount();
        List<Error> errors;
        Long timeStamp = (Long) Instant.now().getEpochSecond();
        if (customer != null) {
            errors = CustomerValidation.validateCustomer(customer);
            if (errors.isEmpty()) {
                for (int i = 0; i < account.size(); i++) {
                    if (account.get(i).getBalance() <= 500) {
                        errors.add(new Error("Minimum balance requirement not met"));
                    } else {
                        account.get(i).setAccount_number((int) (1000 + customerRepository.count()));
                    }
                    customer.getAccount().get(i).setCreatedAt(timeStamp);
                    customer.getAccount().get(i).setUpdatedAt(timeStamp);
                    customer.getAccount().get(i).setIsActive(true);
                    customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));

                }
                customer.setIsActive(true);
                customer.setCreatedAt(timeStamp);
                customer.setUpdatedAt(timeStamp);
                customerRepository.save(customer);
                apiResponse.setData(customer);
                apiResponse.setStatus(201);
                apiResponse.setMessage("Account Created Successfully");
                log.info("Account created Successfully!");
                apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

            } else {
                apiResponse.setStatus(400);
                apiResponse.setData(errors);
                apiResponse.setMessage("Something went wrong");
                log.info("Account creation failed!");
                apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

            }
        }
        return apiResponseResponseEntity;
    }


    //To get Account
    public ResponseEntity<APIResponse> getAccount(Integer account_number) {
        ResponseEntity<APIResponse> apiResponseResponseEntity;
        Account account = accountRepository.findByAccountNumber(account_number);
        if (account != null) {
            apiResponse.setStatus(200);
            apiResponse.setData(account);
            apiResponse.setMessage("Done");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(400);
            apiResponse.setData(new ArrayList<>());
            apiResponse.setError("Invalid account number entered!");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        return apiResponseResponseEntity;
    }


    public ResponseEntity<APIResponse> getAccountsByCustomerId(Integer customerId) {
        ResponseEntity<APIResponse> apiResponseResponseEntity;
        List<Account> account = accountRepository.findAccountsByCustomerId(customerId);
        if (account != null) {
            apiResponse.setStatus(200);
            apiResponse.setData(account);
            apiResponse.setMessage("Done");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(400);
            apiResponse.setData(new ArrayList<>());
            apiResponse.setError("No data found based on the customerID!");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        return apiResponseResponseEntity;
    }
}
