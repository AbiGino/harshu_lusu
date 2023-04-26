package com.BankingApplication.Service;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Common.AccountValidation;
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
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    APIResponse apiResponse;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public ResponseEntity<APIResponse> createAccount(Account account) {
        ResponseEntity<APIResponse> apiResponseResponseEntity;
        List<Error> errors = new ArrayList<>();
        Long timeStamp = (Long) Instant.now().getEpochSecond();
        if (account != null) {
            errors = AccountValidation.validateAccount(account);
            if (errors.isEmpty()) {
                Customer customer = account.getCustomer();
                customer.setIsActive(true);
                customer.setCreatedAt(timeStamp);
                customer.setUpdatedAt(timeStamp);
                account.setCustomer(customer);

                if (account.getBalance() < 500) {
                    errors.add(new Error("Minimum balance requirement not met"));
                    apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                    apiResponse.setMessage("Something went wrong");
                    apiResponse.setData(errors);
                    apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
                } else {
                    account.setAccount_number((int) (1000 + accountRepository.count()));
                    account.setIsActive(true);
                    account.setCreatedAt(timeStamp);
                    account.setUpdatedAt(timeStamp);

                    account.setCustomer(customer);
                    accountRepository.save(account);

                    apiResponse.setData(account);
                    apiResponse.setStatus(HttpStatus.CREATED.value());
                    apiResponse.setMessage("Account Created Successfully");
                    apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
                    log.info("Account created Successfully!");
                }
            } else {
                apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                apiResponse.setMessage("Something went wrong");
                apiResponse.setData(errors);
                apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }
        } else {
            errors.add(new Error("No data for the account"));
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setData(errors);
            apiResponse.setMessage("Something went wrong");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            log.info("Account creation failed!");
        }
        return apiResponseResponseEntity;
    }
}

//        ResponseEntity<APIResponse> apiResponseResponseEntity;
//        List<Error> errors;
////        Customer customer=new Customer();
////        Customer customer = account.getCustomer();
//        if (account != null) {
//            errors = AccountValidation.validateAccount(account);
//            if (errors.isEmpty()) {
//                Customer customer = account.getCustomer();
//                customer.setIsActive(true);
//                customer.setCreatedAt(LocalDateTime.now());
//                customer.setUpdatedAt(LocalDateTime.now());
////                account.setCustomer(customer);
////                accountRepository.save(account);
//                if (account.getBalance() < 500) {
//                    apiResponse.setStatus(400);
//                    apiResponse.setMessage("Something went wrong");
//                    errors.add(new Error("Minimum balance requirement not met"));
//                    apiResponse.setData(errors);
//                    apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//                } else {
//                    account.setAccount_number((int) (1000 + accountRepository.count()));
//                    account.setIsActive(true);
//                    account.setCreatedAt(LocalDateTime.now());
//                    account.setUpdatedAt(LocalDateTime.now());
//
//                    account.setCustomer(customer);
//                    accountRepository.save(account);
//
//                    apiResponse.setData(account);
//                    apiResponse.setStatus(201);
//                    apiResponse.setMessage("Account Created Successfully");
//                    log.info("Account created Successfully!");
//                    apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
//                }
//            } else {
//                apiResponse.setStatus(400);
//                apiResponse.setMessage("Something went wrong");
//                apiResponse.setData(errors);
//                apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//
//            }
//        } else {
//            errors = new ArrayList<>();
//            errors.add(new Error("No data for the account"));
//            apiResponse.setStatus(400);
//            apiResponse.setData(errors);
//            apiResponse.setMessage("Something went wrong");
//            log.info("Account creation failed!");
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//        }
//        return apiResponseResponseEntity;
//    }


//        ResponseEntity<APIResponse> apiResponseResponseEntity;
//        List<Error> errors = new ArrayList<>();
//        if (account != null) {
//            errors = AccountValidation.validateAccount(account);
//            if (errors.isEmpty()) {
//                Customer customer = account.getCustomer();
//                customer.setIsActive(true);
//                customer.setCreatedAt(LocalDateTime.now());
//                customer.setUpdatedAt(LocalDateTime.now());
//                account.setCustomer(customer);
//
//                if (account.getBalance() < 500) {
//                    errors.add(new Error("Minimum balance requirement not met"));
//                    apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//                    apiResponse.setMessage("Something went wrong");
//                    apiResponse.setData(errors);
//                    apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//                } else {
//                    account.setAccount_number((int) (1000 + accountRepository.count()));
//                    account.setIsActive(true);
//                    account.setCreatedAt(LocalDateTime.now());
//                    account.setUpdatedAt(LocalDateTime.now());
//
//                    account.setCustomer(customer);
//                    accountRepository.save(account);
//
//                    apiResponse.setData(account);
//                    apiResponse.setStatus(HttpStatus.CREATED.value());
//                    apiResponse.setMessage("Account Created Successfully");
//                    apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
//                    log.info("Account created Successfully!");
//                }
//            } else {
//                apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//                apiResponse.setMessage("Something went wrong");
//                apiResponse.setData(errors);
//                apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//            }
//        } else {
//            errors.add(new Error("No data for the account"));
//            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//            apiResponse.setData(errors);
//            apiResponse.setMessage("Something went wrong");
//            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//            log.info("Account creation failed!");
//        }
//        return apiResponseResponseEntity;
//    }
//}


//    public APIResponse login(LoginRequestDTO loginRequestDTO) {
//        List<Error> errors= LoginRequestValidation.validateLogin(loginRequestDTO);
//        String name = loginRequestDTO.getName();
//        String password = loginRequestDTO.getPassword();
//        Customer customer = customerRepository.findByName(name);
//        if(errors.isEmpty()) {
//            if (customer == null) {
//                apiResponse.setStatus(400);
//                apiResponse.setData(null);
//                apiResponse.setMessage("No data found");
//            }
//        }else {
//            if (bCryptPasswordEncoder.matches(password, customer.getPassword())) {
//                String token = jwtUtils.generateJwt(customer);
//                Map<String, Object> data = new HashMap<>();
//                data.put("Token", token);
//                apiResponse.setStatus(200);
//                apiResponse.setData(data);
//                apiResponse.setMessage("Logged in successfully");
//            }
//        }
//        return apiResponse;
//    }
//}


