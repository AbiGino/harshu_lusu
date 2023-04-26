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
        ResponseEntity<APIResponse> apiResponseResponseEntity = null;
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
//                    customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));

                }
                customer.setIsActive(true);
                customer.setCreatedAt(timeStamp);
                customer.setUpdatedAt(timeStamp);
                customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));

//                for (int i = 0; i < account.size(); i++) {
//                    customer.getAccount().get(i).setIsActive(true);
//                    customer.getAccount().get(i).setCreatedAt(LocalDateTime.now());
//                    customer.getAccount().get(i).setUpdatedAt(LocalDateTime.now());
//                }
                customerRepository.save(customer);
                apiResponse.setData(customer);
                apiResponse.setStatus(201);
                apiResponse.setMessage("Account Created Successfully");
                log.info("Account created Successfully!");
                apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
                return apiResponseResponseEntity;

            } else {
                errors = new ArrayList<>();
                errors.add(new Error("No data for the account"));
            }
            apiResponse.setStatus(400);
            apiResponse.setData(errors);
            apiResponse.setMessage("Something went wrong");
            log.info("Account creation failed!");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

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


//    public ResponseEntity<APIResponse> transfer(Integer fromAccountNumber, Integer toAccountNumber, Integer balance) {
//        ResponseEntity<APIResponse> apiResponseResponseEntity;
//        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
//        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
//        if (fromAccount != null || toAccount != null) {
//            fromAccount.setBalance(fromAccount.getBalance() - balance);
//            toAccount.setBalance(toAccount.getBalance() + balance);
//            accountRepository.save(fromAccount);
//            accountRepository.save(toAccount);
//            apiResponse.setStatus(200);
//            apiResponse.setMessage("Amount transferred Successfully!");
//            apiResponse.setData("FROM ACCOUNT -> " + fromAccount + " TO ACCOUNT -> " + toAccount);
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
//        } else {
//
//            apiResponse.setStatus(400);
//            apiResponse.setData(new ArrayList<>());
//            apiResponse.setError("Amount Transfer Failed!");
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//        }
//        if (fromAccount.getBalance() < balance) {
//            apiResponse.setError("Insufficient balance!");
//        }
//        return apiResponseResponseEntity;
//    }

//To transfer amount from one account to another
//    public ResponseEntity<APIResponse> transfer(Integer fromAccountNumber, Integer toAccountNumber, Integer balance) {
//        ResponseEntity<APIResponse> apiResponseResponseEntity;
//        Customer fromAccount = customerRepository.findByAccountNumber(fromAccountNumber);
//        Customer toAccount = customerRepository.findByAccountNumber(toAccountNumber);
//        if (fromAccount != null && toAccount != null && balance != null && balance > 0 && fromAccount.getBalance() >= balance) {
//            fromAccount.setBalance(fromAccount.getBalance() - balance);
//            toAccount.setBalance(toAccount.getBalance() + balance);
//            customerRepository.save(fromAccount);
//            customerRepository.save(toAccount);
//            APIResponse apiResponse = new APIResponse();
//            apiResponse.setStatus(200);
//            apiResponse.setMessage("Amount transferred Successfully!");
//            apiResponse.setData("FROM ACCOUNT -> " + fromAccount + " TO ACCOUNT -> " + toAccount);
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
//        } else {
//            List<Error> errors = new ArrayList<>();
//            if (fromAccount == null) {
//                errors.add(new Error("Invalid fromAccountNumber"));
//            }
//            if (toAccount == null) {
//                errors.add(new Error("Invalid toAccountNumber"));
//            }
//            if (balance == null || balance <= 0) {
//                errors.add(new Error("Invalid balance amount"));
//            }
//            if (fromAccount != null && fromAccount.getBalance() < balance) {
//                errors.add(new Error("Insufficient balance in fromAccount"));
//            }
//            APIResponse apiResponse = new APIResponse();
//            apiResponse.setStatus(400);
//            apiResponse.setData(errors);
//            apiResponse.setError("Amount Transfer Failed!");
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//        }
//        return apiResponseResponseEntity;
//    }


//To get transaction details
//    public List<Transaction> getTransaction(Integer account_number) {
//
//        Customer customer = customerRepository.findByAccountNumber(account_number);
//        List<Transaction> transactions = null;
//        List<Error> errors = new ArrayList<>();
//        if (customer == null) {
//// handle invalid account number
//            errors.add(new Error("Invalid account number"));
//        } else {
//            transactions = transactionRepository.findByAccountNumber(account_number);
//
//        }
//        return transactions;
//    }
//}

//if (customer.getAccount().getBalance() < 500) {
//                        errors.add(new Error("Minimum balance requirement not met"));
//                    } else {
//                    customer.setAccount().setAccount_number((int) (1000 + customerRepository.count()));


//                for (int i=0;i < account.size();i++) {
//                    if (account.get(i).getBalance() < 500) {
//                        errors.add(new Error("Minimum balance requirement not met"));
//                    } else {
//                        account.get(i).setAccount_number((int) (1000 + customerRepository.count()));
//                    }
//                    customer.getAccount().get(i).setCreatedAt(LocalDateTime.now());
//                    customer.getAccount().get(i).setUpdatedAt(LocalDateTime.now());
//                    customer.getAccount().get(i).setIsActive(true);
//                }


//        void managedAccountentityManager.persist(customer);
//        Customer managedAccount = entityManager.merge(customer);
//        entityManager.persist(account);


//    public ResponseEntity<APIResponse> createCustomer(Customer customer) {
//        ResponseEntity<APIResponse> apiResponseResponseEntity = null;
//        List<Account> accounts = customer.getAccount();
//        List<Error> errors = new ArrayList<>();
//        boolean valid = true;
//
//        for (Account account : accounts) {
//            if (account.getBalance() < 500) {
//                errors.add(new Error("Minimum balance requirement not met for account type: " + account.getAccount_type()));
//                valid = false;
//            }
//        }
//
//        if (valid) {
//            customer.setIsActive(true);
//            customer.setCreatedAt(LocalDateTime.now());
//            customer.setUpdatedAt(LocalDateTime.now());
//            for (Account account : accounts) {
//                account.setIsActive(true);
//                account.setCreatedAt(LocalDateTime.now());
//                account.setUpdatedAt(LocalDateTime.now());
//                account.setAccount_number(1000 + customerRepository.count());
//            }
//            customerRepository.save(customer);
//            apiResponse.setData(customer);
//            apiResponse.setStatus(201);
//            apiResponse.setMessage("Account Created Successfully");
//            log.info("Account created Successfully!");
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
//        } else {
//            apiResponse.setStatus(400);
//            apiResponse.setData(errors);
//            apiResponse.setMessage("Account creation failed");
//            log.info("Account creation failed!");
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//        }
//
//        return apiResponseResponseEntity;
//    }
//}