package com.BankingApplication.Service;

import com.BankingApplication.Common.APIResponse;
import com.BankingApplication.Common.Error;
import com.BankingApplication.Common.LoginRequestValidation;
import com.BankingApplication.DTO.LoginRequestDTO;
import com.BankingApplication.Entity.Customer;
import com.BankingApplication.Repository.CustomerRepository;
import com.BankingApplication.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    LoginRequestValidation loginRequestValidation;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    public ResponseEntity<APIResponse> login(LoginRequestDTO loginRequestDTO) {
        ResponseEntity<APIResponse> apiResponseResponseEntity=null;
        APIResponse apiResponse = new APIResponse();
        List<Error> errors = LoginRequestValidation.validateLogin(loginRequestDTO);
        String name = loginRequestDTO.getName();
        String password = loginRequestDTO.getPassword();
        if (errors.isEmpty()) {
            Customer customer = customerRepository.findByNameAndPassword(name);
            if (bCryptPasswordEncoder.matches(password, customer.getPassword())) {
                String token = jwtUtils.generateJwt(customer);
                Map<String, Object> data = new HashMap<>();
                data.put("Token", token);
                apiResponse.setStatus(200);
                apiResponse.setData(data);
                apiResponse.setMessage("Logged in Successfully");
                apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                apiResponse.setStatus(400);
                apiResponse.setData(null);
                apiResponse.setMessage("Invalid username or password");
                apiResponseResponseEntity=new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
            }
        }
        return apiResponseResponseEntity;
    }
}


//            if (customer == null) {
//                apiResponse.setStatus(400);
//                apiResponse.setData(null);
//                apiResponse.setMessage("No data found");
//            } else if (bCryptPasswordEncoder.matches(password, customer.getPassword())) {
//                System.out.println(customer.getPassword());
//                String token = jwtUtils.generateJwt(customer);
//                Map<String, Object> data = new HashMap<>();
//                data.put("Token", token);
//                apiResponse.setStatus(200);
//                apiResponse.setData(data);
//                apiResponse.setMessage("Logged in successfully");
//            } else {
//                apiResponse.setStatus(400);
//                apiResponse.setData(null);
//                apiResponse.setMessage("Invalid username or password");
//            }