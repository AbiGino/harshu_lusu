package com.BankingApplication.Controller;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.DTO.RootGetAll;
import com.BankingApplication.DTO.RootGetById;
import com.BankingApplication.Entity.Customer;
import com.BankingApplication.Service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/accounts")
public class CustomerController {

    @Autowired
    RestTemplate restTemplate;
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


    @GetMapping("/getAllAccount/{account_number}")
    public RootGetById getAccountsById(@Validated @PathVariable Integer account_number){
        String apiUrl="http://localhost:8090/api/customer/getcustomer/{account_number}";
        Map<String, Integer> urlParams = new HashMap<>();
        urlParams.put("account_number", account_number);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange(apiUrl, HttpMethod.GET, entity, RootGetById.class,urlParams).getBody();
    }

    @GetMapping("/getallcustomer")
    public RootGetAll getAll(){
        String apiUrl="http://localhost:8090/api/customer/getallcustomer";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return restTemplate.exchange(apiUrl, HttpMethod.GET, entity, RootGetAll.class).getBody();

    }
    @GetMapping("/getallcustomer/{account_number}")
    public RootGetById getAccountsByIdUsingEntity(@Validated @PathVariable Integer account_number){
        String apiUrl="http://localhost:8090/api/customer/getcustomer/{account_number}";
        Map<String, Integer> urlParams = new HashMap<>();
        urlParams.put("account_number", account_number);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<RootGetById> response=restTemplate.getForEntity(apiUrl,RootGetById.class,account_number);
        return response.getBody();
    }



}
