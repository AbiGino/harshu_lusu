package com.BankingApplication.Controller;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Entity.Customer;
import com.BankingApplication.Service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/accounts")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    APIResponse apiResponse;


    @PostMapping("/signup")
    public ResponseEntity<APIResponse> createCustomer(@Validated @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("getAccount/{account_number}")
    public ResponseEntity<APIResponse> getAccount(@Validated @PathVariable Integer account_number) throws Exception {
        return customerService.getAccount(account_number);
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<APIResponse> getAccountsByCustomerId(@PathVariable Integer customer_id) {
        return customerService.getAccountsByCustomerId(customer_id);
    }

//    @PostMapping("/{fromAccountNumber}/transfer/{toAccountNumber}")
//    public ResponseEntity<APIResponse> transfer(@Validated @PathVariable Integer fromAccountNumber, @PathVariable Integer toAccountNumber, @RequestParam Integer balance) {
//        log.info("Amount Transferred Successfully");
//        return customerService.transfer(fromAccountNumber, toAccountNumber, balance);
//    }

//    @PostMapping("/getTransaction/{account_number}")
//    public List<Transaction> getTransactionDetails(@Validated @PathVariable Integer account_number) throws Exception{
//        return customerService.getTransaction(account_number);
//    }


}
